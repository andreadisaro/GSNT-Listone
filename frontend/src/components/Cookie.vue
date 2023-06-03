<script setup>
import { ref, onMounted } from "vue";
import useScript from '../utils/useScript';
const showBlock = ref(true);
const isProd = location.hostname.indexOf("p.acigo.it") > -1;
var _iub = window._iub || [];
//console.log("iubenda", _iub)
_iub.csConfiguration = {
    askConsentAtCookiePolicyUpdate: true,
    perPurposeConsent: true,
    whitelabel: false,
    lang: "it",
    siteId: isProd ? 3142982 : 3121261,
    cookiePolicyId: isProd ? 68000312 : 28035824,
    consentOnScroll: false, //avoid consent to be given when the user scrolls the page
    banner: {
        position: "float-bottom-center",
        acceptButtonDisplay: true,
        customizeButtonDisplay: true,
        backgroundColor: "white",
        textColor: "black",
        acceptButtonColor: "#222222",
        customizeButtonColor: "#E2E2E2",
        customizeButtonCaptionColor: "black",
        listPurposes: true,
        customizeButtonCaption: "Visualizza la Privacy Policy e la Cookie Policy",
    },
    callback: {
        onPreferenceExpressed: function (preference) {
            //console.log("onPreferenceExpressed", preference)
            if (preference.purposes[1]) {
                showBlock.value = false;
                //console.log("generico")
            }
            if (preference.purposes[4]) {
                //console.log("matomo")
                var _paq = (window._paq = window._paq || []);
                _paq.push(['rememberCookieConsentGiven']);
            }
        }
    },
    skipSaveConsent: false, // mettere true per debuggare (esce ogni volta il banner)
};
//_iub.csConfiguration = { "banner": { "closeButtonRejects": true, "customizeButtonDisplay": true, "explicitWithdrawal": true, "listPurposes": true, "logo": null, "position": "bottom" } };
onMounted(() => {
    useScript("//cdn.iubenda.com/cs/iubenda_cs.js")
})
</script>

<template>
    <div className="flex fixed inset-0 items-center justify-center z-50 background" v-if="showBlock">
        <div className="flex items-center justify-center">
        </div>
    </div>
</template>

<style scoped>
.background {
    background: rgba(85, 85, 85, 0.65);
}
</style>
