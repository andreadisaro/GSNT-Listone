import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useDataStore = defineStore("data", () => {
  const eventTitle = ref("");
  const eventLogo = ref("");
  const items = ref({});
  const categories = ref([]);
  function loadData() {
    eventTitle.value = localStorage.getItem("eventTitle") || "";
    eventLogo.value = localStorage.getItem("eventLogo") || "";
    items.value = JSON.parse(localStorage.getItem("items")) || {};
    categories.value = JSON.parse(localStorage.getItem("categories")) || [];
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
    localStorage.setItem("items", JSON.stringify(itemsTmp));
    localStorage.setItem("categories", JSON.stringify(categoriesTmp));
  }

  /*const getToken = computed(() => {
    return token.value || localStorage.getItem("token") || "";
  });*/

  return {
    eventTitle,
    eventLogo,
    items,
    categories,
    loadData,
    setEventTitle,
    setEventLogo,
    setItems,
  };
});
