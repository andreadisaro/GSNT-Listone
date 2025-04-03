import { ref, watch, computed } from "vue";
import { defineStore } from "pinia";
import fakeItems from "./fakeItems";
import { APISettings } from "../api/config.js";
import { useLoadingStore } from "./useLoadingStore.js";

export const useDataStore = defineStore("data", () => {
  const eventTitle = ref(localStorage.getItem("eventTitle") || "");
  const eventLogo = ref(localStorage.getItem("eventLogo") || "");
  const items_ = ref(JSON.parse(localStorage.getItem("items")) || {});
  const editors = ref(JSON.parse(localStorage.getItem("editors")) || []);
  const categories = ref(JSON.parse(localStorage.getItem("categories")) || []);
  const journalistsDays = ref(
    JSON.parse(localStorage.getItem("journalistsDays")) || {}
  );
  const days = ref(JSON.parse(localStorage.getItem("days")) || []);
  const showMenu = ref(false);
  const showMenuButton = ref(true);
  const goToRef = ref(null);
  const showFilter = ref(localStorage.getItem("showFilter") || "showAll");
  const makePdf = ref(false);
  function setMakePdfTrue() {
    makePdf.value = true;
  }
  function setShowFilter(showFilterP) {
    showFilter.value = showFilterP;
  }
  function setEventTitle(eventTitleP) {
    eventTitle.value = eventTitleP;
    localStorage.setItem("eventTitle", eventTitleP);
  }
  function setEventLogo(eventLogoP) {
    eventLogo.value = eventLogoP;
    localStorage.setItem("eventLogo", eventLogoP);
  }
  function setFakeItems() {
    items_.value = fakeItems;
  }
  function setItems(errorFunction) {
    fetch("/be/public/api/items", {
      method: "GET",
      headers: APISettings.headers,
    })
      .then(function (response) {
        if (response.status != 200) {
          console.error(response.status);
          if (Object.entries(this.items).length == 0 && errorFunction)
            errorFunction();
        } else {
          response.json().then((itemsP) => {
            //console.log(res);
            let itemsTmp = {};
            let categoriesTmp = [];
            let editorsTmp = [];
            //sort itemsP by category.ordering and editor.name and item.name
            itemsP = itemsP.sort((a, b) => {
              if (a.category.ordering > b.category.ordering) {
                return 1;
              }
              if (a.category.ordering < b.category.ordering) {
                return -1;
              }
              if (a.editor.name > b.editor.name) {
                return 1;
              }
              if (a.editor.name < b.editor.name) {
                return -1;
              }
              if (a.name > b.name) {
                return 1;
              }
              if (a.name < b.name) {
                return -1;
              }
              return 0;
            });

            console.log("itemsP", itemsP);

            for (let i = 0; i < itemsP.length; i++) {
              let item = itemsP[i];
              if (
                items_.value[item.category.name] &&
                items_.value[item.category.name][item.editor.id]
              ) {
                let index = items_.value[item.category.name][
                  item.editor.id
                ].findIndex((element) => element.id === item.id);
                item = {
                  ...items_.value[item.category.name][item.editor.id][index],
                  ...item,
                };
              }
              if (!categoriesTmp.includes(item.category.name)) {
                categoriesTmp.push(item.category.name);
              }
              if (!itemsTmp[item.category.name]) {
                itemsTmp[item.category.name] = {};
              }
              if (!itemsTmp[item.category.name][item.editor.id]) {
                itemsTmp[item.category.name][item.editor.id] = [];
                if (
                  editorsTmp.findIndex(
                    (element) => element.id === item.editor.id
                  ) === -1
                )
                  editorsTmp.push(item.editor);
              }
              itemsTmp[item.category.name][item.editor.id].push(item);
            }

            items_.value = itemsTmp;
            editorsTmp = editorsTmp.sort((a, b) => {
              if (a.name > b.name) {
                return 1;
              }
              if (a.name < b.name) {
                return -1;
              }
              return 0;
            });
            editors.value = editorsTmp;
            categories.value = categoriesTmp;
            console.log("items_", itemsTmp);
            console.log("editors", editorsTmp);
            console.log("categories", categoriesTmp);
            localStorage.setItem("categories", JSON.stringify(categoriesTmp));
            localStorage.setItem("editors", JSON.stringify(editorsTmp));
          });
        }
      })
      .finally(() => {
        const loadingStore = useLoadingStore();
        loadingStore.removeLoading();
      });
  }
  const items = computed(() => {
    let ret = {};
    for (let category in items_.value) {
      for (let editor in items_.value[category]) {
        for (let i = 0; i < items_.value[category][editor].length; i++) {
          let go = false;
          if (showFilter.value == "showAll") {
            go = true;
          } else if (showFilter.value == "showOnlyLook") {
            go = items_.value[category][editor][i].look;
          } else if (showFilter.value == "showOnlyPlay") {
            go = items_.value[category][editor][i].play;
          } else if (showFilter.value == "showOnlyBuy") {
            go = items_.value[category][editor][i].buy;
          } else if (showFilter.value == "showOnlySomething") {
            go =
              items_.value[category][editor][i].look ||
              items_.value[category][editor][i].play ||
              items_.value[category][editor][i].buy;
          } else if (showFilter.value == "showOnlyNothing") {
            go =
              !items_.value[category][editor][i].look &&
              !items_.value[category][editor][i].play &&
              !items_.value[category][editor][i].buy;
          }
          if (go) {
            if (!ret[category]) {
              ret[category] = {};
            }
            if (!ret[category][editor]) {
              ret[category][editor] = [];
            }
            ret[category][editor].push(items_.value[category][editor][i]);
          }
        }
      }
    }
    console.log("items", ret);
    return ret;
  });
  watch(
    () => items_.value,
    (itemsP) => {
      localStorage.setItem("items", JSON.stringify(itemsP));
    },
    { deep: true }
  );
  function setJournalistsDays(journalistsDaysP) {
    let ret = {};
    let daysI = [];
    //sort journalistsDaysP by id.eventDay.id
    journalistsDaysP = journalistsDaysP.sort((a, b) => {
      if (a.id.eventDay.id > b.id.eventDay.id) {
        return 1;
      }
      if (a.id.eventDay.id < b.id.eventDay.id) {
        return -1;
      }
      return 0;
    });
    //initialize days from journalistsDaysP.day unique values
    for (let i = 0; i < journalistsDaysP.length; i++) {
      let day = journalistsDaysP[i].day;
      if (!daysI.includes(day)) {
        daysI.push(day);
      }
    }
    //sort journalistsDaysP by journalist
    journalistsDaysP = journalistsDaysP.sort((a, b) => {
      if (a.journalist > b.journalist) {
        return 1;
      }
      if (a.journalist < b.journalist) {
        return -1;
      }
      return 0;
    });
    for (let i = 0; i < journalistsDaysP.length; i++) {
      let day = journalistsDaysP[i].day;
      let journalist = journalistsDaysP[i].journalist;
      if (!ret[journalist]) {
        ret[journalist] = {};
      }
      ret[journalist][day] = journalistsDaysP[i].presence;
    }
    //fill ret with all days
    for (let i = 0; i < journalistsDaysP.length; i++) {
      let journalist = journalistsDaysP[i].journalist;
      for (let j = 0; j < daysI.length; j++) {
        if (!ret[journalist].hasOwnProperty(daysI[j])) {
          ret[journalist][daysI[j]] = false;
        }
      }
    }
    journalistsDays.value = ret;
    console.log("journalistsDays", ret);
    days.value = daysI;
    console.log("days", daysI);
    localStorage.setItem("journalistsDays", JSON.stringify(ret));
    localStorage.setItem("days", JSON.stringify(daysI));
  }

  /*const getToken = computed(() => {
    return token.value || localStorage.getItem("token") || "";
  });*/

  return {
    eventTitle,
    eventLogo,
    items,
    editors,
    categories,
    journalistsDays,
    days,
    showMenu,
    goToRef,
    showFilter,
    makePdf,
    showMenuButton,
    setMakePdfTrue,
    setShowFilter,
    setEventTitle,
    setEventLogo,
    setItems,
    setFakeItems,
    setJournalistsDays,
  };
});
