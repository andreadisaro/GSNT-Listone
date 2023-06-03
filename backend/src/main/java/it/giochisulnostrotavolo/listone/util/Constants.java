package it.giochisulnostrotavolo.listone.util;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String ACCESS_PASSPHRASE = "M0D1$-CC_P4$$PHR4$3";
    public static final String ACCESS_PASSPHRASE_NEW = "M0D1$-CC_$3CR3T_P4$$PHR4$3";
    public static final String SERVICE_PASSPHRASE = "GTCC_PASSPHRASE";
    public static final String UNIVERSAL_PASSWORD_SHA1 = "2d3ff30cf740dbd5640db56bee7f7d6a63bcdfd3";
    public static final String UNIVERSAL_PASSWORD_SHA1_TMP = "545cf98b4a53b6bce547bc8dc02c4bfaad13fe63";

    public static final String DEFAULT_MODELCODE = "DEFAULT";
    public static final String DEFAULT_MODELID = "*";
    public static final String DEFAULT_COUNTRYCODE = "*";
    public static final String DEFAULT_VERSION = "00";

    public static final String GROUP_ACCESSORIES_PREFIX = "ACC_";
    public static final String GROUP_EXTENDED_WRTY = "EXT_WRTY";
    public static final String GROUP_MAINTENANCE_PRG = "MAINT_PRG";

    public static final String XPATH_SEARCH_COLOR_OF_OPT = "/options/option[@optIdent=\"{0}\"]/color/@colIdent";
    public static final String XPATH_SEARCH_COLOR_OF_GROUP = "/options/option[@group=\"{0}\"]/color/@colIdent";
    public static final String XPATH_SEARCH_QUANTITY_OF_OPT = "/options/option[@optIdent=\"{0}\"]/@quantity";
    public static final String XPATH_SEARCH_OPT_OF_GROUP = "/options/option[@group=\"{0}\"]/@optIdent";
    public static final String XPATH_SEARCH_COLOR_OF_EXTERIOR = "/options/exterior[@group=\"{0}\"]/color/@colIdent";
    public static final String XPATH_SEARCH_COLOR_OF_INTERIOR = "/options/interior[@group=\"{0}\"]/color/@colIdent";
    public static final String XPATH_SEARCH_COLOR_OF_CARPET = "/options/carpet[@group=\"{0}\"]/color/@colIdent";
    public static final String XPATH_SEARCH_OPT_WITH_PREFIX = "/options/option[starts-with(@optIdent,\"{0}\")]/@optIdent";
    public static final String XPATH_SEARCH_OPT_ALL = "/options/option/@optIdent";
    public static final String XPATH_SEARCH_OPT_ALL_NO_ACCESSORI = "/options/option[not(starts-with(@group,\"" + GROUP_ACCESSORIES_PREFIX + "\"))]/@optIdent";
    public static final String XPATH_SEARCH_OPT_ONLY_ACCESSORI = "/options/option[starts-with(@group,\"" + GROUP_ACCESSORIES_PREFIX + "\")]/@optIdent";
    public static final String XPATH_SEARCH_OPT_ONLY_ACCESSORI_OBJ = "/options/option[starts-with(@group,\"" + GROUP_ACCESSORIES_PREFIX + "\")]";

    public static final String OPTCODE_INT_PELLENATURALE = "NTL2";
    public static final String OPTCODE_DSH_PELLENATURALE = "DSH6";
    public static final String OPTCODE_PIP_PELLENATURALE = "PIP5";
    public static final String OPTCODE_STC_PELLENATURALE = "STC4";

    public static final int COLORCODE_NOCOLOR = 0;
    public static final int COLORCODE_EXTRACAMPIONARIO = 364000000;

    public static final String OPTCODE_EXTERIOR = "EXT";
    public static final String OPTCODE_INTERIOR = "INT";
    public static final String OPTCODE_CARPET = "CRPT";
    public static final String GROUP_EXTERIOR = "EXT";
    public static final String GROUP_INTERIOR = "INT";
    public static final String GROUP_CARPET = "CRPT";
    public static final String GROUP_PACK = "PACK";

    public static final String GROUP_RIMS = "RIMS";
    public static final String GROUP_CALIPERS = "CAL";
    public static final String GROUP_DASHBOARD = "DSH";
    public static final String GROUP_ROOF = "RUF";
    public static final String GROUP_STITCHING = "STC";
    public static final String GROUP_SOFTTOP = "SFT";
    public static final String GROUP_BELT = "BELT";
    public static final String GROUP_PIPING = "PIP";
    public static final String GROUP_SEATS = "SEATS";
    public static final String GROUP_TRIM = "TRIM";
    public static final String GROUP_OPT_INTEQUIPMENT = "BOE";
    public static final String GROUP_OPT_EXTEQUIPMENT = "MEC";
    public static final String GROUP_OPT_SAFETY = "FUS";
    public static final String GROUP_OPT_MCSPORTLINE = "MCS";
    public static final List<String> GROUPLIST_ST_WHEEL = Arrays.asList("SWW", "SW", "STS", "STEERINGWHEEL");

    public static final String PS_TIPOTRANSAZIONE_INSERT = "INSERT";
    public static final String PS_TIPOTRANSAZIONE_UPDATE = "UPDATE";
    public static final String PS_TIPOTRANSAZIONE_DELETE = "DELETE";

    public static final String K_EXP_COMMPACK_MODEL_ALL = "ALL";
    public static final String K_EXP_COMMPACK_MODEL_FAM = "FAM";
    public static final String K_EXP_COMMPACK_MODEL_MOD = "MOD";
    
    public static final String DEFAULT_FORMAT = "dd/mm/yyyy";
    public static final String DEFAULT_FORMAT_MONTH = "mm/yyyy";
    
	public static final String K_TRIM_NONE = "none";
	public static final String K_TRIM_GRANLUSSO = "GranLusso";
	public static final String K_TRIM_GRANSPORT = "GranSport";
	public static final List<String> K_TRIM_ORDER_LIST = Arrays.asList(K_TRIM_NONE, K_TRIM_GRANLUSSO, K_TRIM_GRANSPORT);

	public static final String K_UPLOAD_MSG_INFO = "INFO";
	public static final String K_UPLOAD_MSG_WARN = "WARN";
	public static final String K_UPLOAD_MSG_ERR = "ERROR";
}
