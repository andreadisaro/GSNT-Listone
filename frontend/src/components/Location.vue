<script setup>
import { ref, onMounted } from "vue";
import { Loader } from "@googlemaps/js-api-loader"
import { useI18n } from "vue-i18n";
import { useDataStore } from "../store/useDataStore";
import { useLoadingStore } from "../store/useLoadingStore";
import * as Sentry from "@sentry/vue";
const dataStore = useDataStore();
const loadingStore = useLoadingStore();
const i18n = useI18n();
const currentLocale = ref(i18n.locale);
const loader = new Loader({
  apiKey: "AIzaSyC1OHiM7xlsxsSogwtQXIGcujgcSM6gppI",
  language: currentLocale.value,
  libraries: ["places"]
});
const props = defineProps({
  text: String,
  name: String,
  placeholder: String,
  modelValue: String,
  required: Boolean,
  validationRegex: String,
});
const emit = defineEmits(["update:modelValue", "isValid"]);
const error = ref(false);
const autocompleteRef = ref(null);
const initAutocomplete = () => {
  const autocomplete = new google.maps.places.Autocomplete(autocompleteRef.value, { types: ["address"], componentRestrictions: { country: "it" }, fields: ["formatted_address", "address_components", "geometry"] });
  google.maps.event.addListener(autocomplete, 'place_changed', () => {
    // Codice per gestire la risposta
    const place = autocomplete.getPlace();
    //console.log(place);
    emit("isValid", true);
    emit("update:modelValue", place.formatted_address);
    dataStore.setLuogoDelFermoLat(place.geometry.location.lat());
    dataStore.setLuogoDelFermoLon(place.geometry.location.lng());
    for (const component of place.address_components) {
      const componentType = component.types[0];

      switch (componentType) {
        case "street_number": {
          dataStore.setCivico(component.long_name);
          break;
        }

        case "route": {
          dataStore.setVia(component.short_name);
          break;
        }
        case "locality":
          dataStore.setComune(component.long_name);
          break;
        case "administrative_area_level_2": {
          dataStore.setProvincia(component.short_name);
          break;
        }

        /*case "postal_code": {
          postcode = `${component.long_name}${postcode}`;
          break;
        }
 
        case "postal_code_suffix": {
          postcode = `${postcode}-${component.long_name}`;
          break;
        }
        case "country":
          document.querySelector("#country").value = component.long_name;
          break;*/
      }
    }
  });
}
onMounted(() => {
  if (typeof google === 'object' && typeof google.maps === 'object') {
    getLocation();
    initAutocomplete();
  }
  else {
    loader.load().then(() => {
      getLocation();
      initAutocomplete();
    });
  }
})
function getLocation() {
  emit("isValid", false);
  if (navigator.geolocation) {
    loadingStore.addLoading();
    navigator.geolocation.getCurrentPosition(showPosition, showError, {
      enableHighAccuracy: true,
      timeout: 10000,
    });
  } else {
    alert(i18n.t("location.inserisciManualmente"));
    var _paq = (window._paq = window._paq || []);
    _paq.push(['trackEvent', 'Errori', 'Geolocalizzazione', 'no navigator.geolocation']);
  }
}
function showPosition(position) {
  //console.log(position);
  const geocoder = new google.maps.Geocoder();
  geocoder
    .geocode({ location: { lat: position.coords.latitude, lng: position.coords.longitude } })
    .then((response) => {
      if (response.results[0]) {
        //console.log(response.results[0]);
        dataStore.setLuogoDelFermoLat(position.coords.latitude);
        dataStore.setLuogoDelFermoLon(position.coords.longitude);
        dataStore.setComune(response.results[0].address_components[3].short_name);
        dataStore.setProvincia(response.results[0].address_components[4].short_name);
        dataStore.setVia(response.results[0].address_components[1].short_name);
        dataStore.setCivico(response.results[0].address_components[0].short_name);

        emit("isValid", true);
        emit("update:modelValue", response.results[0].formatted_address);
      } else {
        alert(i18n.t("location.inserisciManualmente"));
      }
    })
    .catch((e) => {
      Sentry.captureException(new Error(e.message, { cause: e.stack }));
      console.error(e);
      var _paq = (window._paq = window._paq || []);
      _paq.push(['trackEvent', 'Errori', 'Geolocalizzazione', e.message]);
    })
    .finally(() => {
      loadingStore.removeLoading();
    })
}
function showError(error) {
  loadingStore.removeLoading();
  var _paq = (window._paq = window._paq || []);
  _paq.push(['trackEvent', 'Errori', 'Geolocalizzazione', error.code]);
  alert(i18n.t("location.inserisciManualmente"));
  /*
  switch (error.code) {
    case error.PERMISSION_DENIED:
      alert("User denied the request for Geolocation.");
      break;
    case error.POSITION_UNAVAILABLE:
      alert("Location information is unavailable.");
      break;
    case error.TIMEOUT:
      alert("The request to get user location timed out.");
      break;
    case error.UNKNOWN_ERROR:
      alert("An unknown error occurred.");
      break;
  }*/
}
</script>

<template>
  <div class="input-container">
    <div class="text-lg font-bold">{{ text }}<sup v-if="required">*</sup></div>
    <input :value="modelValue" :placeholder="placeholder" class="input" ref="autocompleteRef" :class="{ error: error }"
      :name="name" :data-matomo-name="name" />
  </div>
</template>

<style scoped>
.input-container {
  position: relative;
  width: 100%;
  text-align: left;
  outline: none;
  height: 47px;
  line-height: 47px;
  font-size: 22px;
  height: auto;
}

.input {
  background-color: transparent;
  border-radius: 0px;
  border-bottom: 1px solid #6b6a6a;
  color: #000;
  padding-left: 1em;
  cursor: pointer;
  user-select: none;
  font-size: 16px;
  width: 100%;
  background-image: url('data:image/svg+xml;utf8,<svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M12 2C8.13 2 5 5.13 5 9C5 14.25 12 22 12 22C12 22 19 14.25 19 9C19 5.13 15.87 2 12 2ZM7 9C7 6.24 9.24 4 12 4C14.76 4 17 6.24 17 9C17 11.88 14.12 16.19 12 18.88C9.92 16.21 7 11.85 7 9Z" fill="black"/><path d="M12 11.5C13.3807 11.5 14.5 10.3807 14.5 9C14.5 7.61929 13.3807 6.5 12 6.5C10.6193 6.5 9.5 7.61929 9.5 9C9.5 10.3807 10.6193 11.5 12 11.5Z" fill="black"/></svg>');
  background-repeat: no-repeat;
  background-position-x: 95%;
  background-position-y: 50%;
  padding-right: 12%;
}

.input.error {
  border-bottom: 2px solid red;
  color: red;
}

.input:focus {
  outline: none;
}

.input::placeholder {
  font-style: italic;
  font-weight: 200;
}

.inputHidden {
  display: none;
}
</style>
