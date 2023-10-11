import { ref, watch } from "vue";
import { defineStore } from "pinia";

export const useDataStore = defineStore("data", () => {
  const eventTitle = ref("");
  const eventLogo = ref("");
  const items = ref({});
  const categories = ref([]);
  const journalistsDays = ref({});
  const days = ref([]);
  function loadData() {
    eventTitle.value = localStorage.getItem("eventTitle") || "";
    eventLogo.value = localStorage.getItem("eventLogo") || "";
    items.value = JSON.parse(localStorage.getItem("items")) || {};
    categories.value = JSON.parse(localStorage.getItem("categories")) || [];
    journalistsDays.value =
      JSON.parse(localStorage.getItem("journalistsDays")) || {};
    days.value = JSON.parse(localStorage.getItem("days")) || [];
  }
  function setEventTitle(eventTitleP) {
    eventTitle.value = eventTitleP;
    localStorage.setItem("eventTitle", eventTitleP);
  }
  function setEventLogo(eventLogoP) {
    eventLogo.value = eventLogoP;
    localStorage.setItem("eventLogo", eventLogoP);
  }
  function setItems(itemsP) {
    let itemsTmp = {};
    let categoriesTmp = [];
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

    for (let i = 0; i < itemsP.length; i++) {
      let item = itemsP[i];
      if (
        items.value[item.category.name] &&
        items.value[item.category.name][item.editor.name]
      ) {
        let index = items.value[item.category.name][item.editor.name].findIndex(
          (element) => element.id === item.id
        );
        item = {
          ...items.value[item.category.name][item.editor.name][index],
          ...item,
        };
      }
      if (!categoriesTmp.includes(item.category.name)) {
        categoriesTmp.push(item.category.name);
      }
      if (!itemsTmp[item.category.name]) {
        itemsTmp[item.category.name] = {};
      }
      if (!itemsTmp[item.category.name][item.editor.name]) {
        itemsTmp[item.category.name][item.editor.name] = [];
      }
      itemsTmp[item.category.name][item.editor.name].push(item);
    }

    items.value = itemsTmp;
    categories.value = categoriesTmp;
    console.log("items", itemsTmp);
    console.log("categories", categoriesTmp);
    localStorage.setItem("categories", JSON.stringify(categoriesTmp));
  }
  watch(
    () => items.value,
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
    categories,
    journalistsDays,
    days,
    loadData,
    setEventTitle,
    setEventLogo,
    setItems,
    setJournalistsDays,
  };
});
