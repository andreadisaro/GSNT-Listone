import { ref, computed } from "vue";
import { defineStore } from "pinia";
import Dot from "../assets/dot.png";
import BMWLogo from "../assets/client_logos/png/bmw.png";
import CFMotoLogo from "../assets/client_logos/png/cfmoto.png";
import KymcoLogo from "../assets/client_logos/png/kymco.png";
import LifanLogo from "../assets/client_logos/png/lifan.png";
import VolvoLogo from "../assets/client_logos/volvo.svg";
import SubaruLogo from "../assets/client_logos/subaru.svg";
import VogeLogo from "../assets/client_logos/voge.svg";
import YadeaLogo from "../assets/client_logos/yadea.svg";
import DucatiLogo from "../assets/client_logos/ducati.svg";
import BMWMotorradLogo from "../assets/client_logos/png/bmw-motorrad.png";

const BMW = {
  nome: "BMW",
  img: BMWLogo,
};

const CFMoto = {
  nome: "CFMoto",
  img: CFMotoLogo,
  privacyLink: "https://cfmotoitaly.it/privacy-policy",
};

const Kymco = {
  nome: "Kymco",
  img: KymcoLogo,
  privacyLink: "https://kymco.it/privacy-policy",
};

const Lifan = {
  nome: "Lifan",
  img: LifanLogo,
};

const Volvo = {
  nome: "Volvo",
  img: VolvoLogo,
};

const Subaru = {
  nome: "Subaru",
  img: SubaruLogo,
  privacyLink: "https://subaru.it/privacy-policy",
};

const Voge = {
  nome: "Voge",
  img: VogeLogo,
  privacyLink: "https://vogeitaly.it/privacy-policy",
};

const Yadea = {
  nome: "Yadea",
  img: YadeaLogo,
  privacyLink: "https://it.yadea.com/privacy",
};

const Ducati = {
  nome: "Ducati",
  img: DucatiLogo,
};

const BMWMotorrad = {
  nome: "BMW Motorrad",
  img: BMWMotorradLogo,
};

const noLogo = {
  nome: "",
  img: Dot,
  privacyLink: "https://www.yoursite.it/privacy-policy",
};

const clienti = {
  "": noLogo,
  111: noLogo, //Numero cablato per demo
  "0266165996": noLogo, //Numero di test che punta a test
  "0266165326": BMW,
  "0266165966": Kymco,
  "0266165651": Subaru,
  "0266165994": Subaru, //Numero di test che punta a prod
  "0266165548": Volvo,
  "0266165610": Ducati,
  "0266165613": Ducati,
  "0266165329": BMWMotorrad,
  "0266165127": Voge,
  "0266165140": CFMoto,
  "0266165846": Lifan,
  "0266165176": Yadea,
};

export const useDataStore = defineStore("data", () => {
  const sendData = ref(false);
  const error = ref(false);
  const errorType = ref("generico");
  const showErrorCaption2 = ref(false);
  const demo = ref(false);
  const dossier = ref("");
  const variant = ref("");
  const token = ref("");
  const numCell = ref("");
  const numChiamato = ref("");
  const targa = ref("");
  const telaio = ref("");
  const nome = ref("");
  const cognome = ref("");
  const km = ref(0);
  const numPasseggeriAdulti = ref(null);
  const numPasseggeriBambini = ref(null);
  const evento = ref("");
  const sottoEvento = ref("");
  const autostrada = ref(null);
  const estero = ref(null);
  const luogoDelFermoLat = ref("");
  const luogoDelFermoLon = ref("");
  const comune = ref("");
  const provincia = ref("");
  const via = ref("");
  const civico = ref("");
  const prodotto = ref("");
  function setSendData(sendDataP) {
    sendData.value = sendDataP;
  }
  function setError(errorP) {
    error.value = errorP;
    localStorage.setItem("error", errorP);
    localStorage.setItem("endDate", new Date().getTime());
  }
  function setDemo(demoP) {
    demo.value = demoP;
  }
  function setVariant(variantP) {
    variant.value = variantP;
  }
  function setErrorType(error_typeP) {
    errorType.value = error_typeP;
    localStorage.setItem("errorType", error_typeP);
  }
  function setShowErrorCaption2(showErrorCaption2P) {
    showErrorCaption2.value = showErrorCaption2P;
    localStorage.setItem("showErrorCaption2", showErrorCaption2P);
  }
  function setDossier(dossierP) {
    dossier.value = dossierP;
    localStorage.setItem("dossier", dossierP);
  }
  function setToken(tokenP) {
    token.value = tokenP;
    localStorage.setItem("token", tokenP);
  }
  function setNumCell(numCellP) {
    numCell.value = numCellP;
    localStorage.setItem("numCell", numCellP);
  }
  function setNumChiamato(numChiamatoP) {
    numChiamato.value = numChiamatoP;
    localStorage.setItem("numChiamato", numChiamatoP);
  }
  function setTarga(targaP) {
    targa.value = targaP;
    localStorage.setItem("targa", targaP);
  }
  function setTelaio(telaioP) {
    telaio.value = telaioP;
    localStorage.setItem("telaio", telaioP);
  }
  function setNome(nomeP) {
    nome.value = nomeP;
    localStorage.setItem("nome", nomeP);
  }
  function setCognome(cognomeP) {
    cognome.value = cognomeP;
    localStorage.setItem("cognome", cognomeP);
  }
  function setKm(kmP) {
    km.value = kmP;
    localStorage.setItem("km", kmP);
  }
  function setNumPasseggeriAdulti(numPasseggeriAdultiP) {
    numPasseggeriAdulti.value = numPasseggeriAdultiP;
    localStorage.setItem("numPasseggeriAdulti", numPasseggeriAdultiP);
  }
  function setNumPasseggeriBambini(numPasseggeriBambiniP) {
    numPasseggeriBambini.value = numPasseggeriBambiniP;
    localStorage.setItem("numPasseggeriBambini", numPasseggeriBambiniP);
  }
  function setEvento(eventoP) {
    evento.value = eventoP;
    localStorage.setItem("evento", eventoP);
  }
  function setSottoEvento(sottoEventoP) {
    sottoEvento.value = sottoEventoP;
    localStorage.setItem("sottoEvento", sottoEventoP);
  }
  function setAutostrada(autostradaP) {
    autostrada.value = autostradaP;
    localStorage.setItem("autostrada", autostradaP);
  }
  function setEstero(esteroP) {
    estero.value = esteroP;
    localStorage.setItem("estero", esteroP);
  }
  function setLuogoDelFermoLat(luogoDelFermoLatP) {
    luogoDelFermoLat.value = luogoDelFermoLatP;
    localStorage.setItem("luogoDelFermoLat", luogoDelFermoLatP);
  }
  function setLuogoDelFermoLon(luogoDelFermoLonP) {
    luogoDelFermoLon.value = luogoDelFermoLonP;
    localStorage.setItem("luogoDelFermoLon", luogoDelFermoLonP);
  }
  function setComune(comuneP) {
    comune.value = comuneP;
    localStorage.setItem("comune", comuneP);
  }
  function setProvincia(provinciaP) {
    provincia.value = provinciaP;
    localStorage.setItem("provincia", provinciaP);
  }
  function setVia(viaP) {
    via.value = viaP;
    localStorage.setItem("via", viaP);
  }
  function setCivico(civicoP) {
    civico.value = civicoP;
    localStorage.setItem("civico", civicoP);
  }
  function setProdotto(prodottoP) {
    prodotto.value = prodottoP;
    localStorage.setItem("prodotto", prodottoP);
  }

  const getNomeCliente = computed(() => {
    return clienti[numChiamato.value]
      ? clienti[numChiamato.value].nome
      : clienti[""].nome;
  });
  const getImgCliente = computed(() => {
    return clienti[numChiamato.value]
      ? clienti[numChiamato.value].img
      : clienti[""].img;
  });
  const getPrivacyLink = computed(() => {
    return clienti[numChiamato.value]
      ? clienti[numChiamato.value].privacyLink
      : undefined;
  });
  const getToken = computed(() => {
    return token.value || localStorage.getItem("token") || "";
  });
  const getIsVariantOriginal = computed(() => {
    if (!variant.value)
      setVariant(localStorage.getItem("variant") || "original");
    return variant.value === "original";
  });

  return {
    sendData,
    error,
    errorType,
    showErrorCaption2,
    demo,
    dossier,
    numCell,
    numChiamato,
    targa,
    telaio,
    nome,
    cognome,
    km,
    numPasseggeriAdulti,
    numPasseggeriBambini,
    evento,
    sottoEvento,
    autostrada,
    estero,
    luogoDelFermoLat,
    luogoDelFermoLon,
    comune,
    provincia,
    via,
    civico,
    prodotto,
    setDossier,
    setVariant,
    setSendData,
    setError,
    setDemo,
    setProdotto,
    setTarga,
    setTelaio,
    setNome,
    setCognome,
    setKm,
    setNumPasseggeriAdulti,
    setNumPasseggeriBambini,
    setEvento,
    setSottoEvento,
    setAutostrada,
    setEstero,
    setLuogoDelFermoLat,
    setLuogoDelFermoLon,
    setComune,
    setProvincia,
    setVia,
    setCivico,
    setErrorType,
    setShowErrorCaption2,
    setToken,
    setNumCell,
    setNumChiamato,
    getNomeCliente,
    getImgCliente,
    getPrivacyLink,
    getToken,
    getIsVariantOriginal,
  };
});
