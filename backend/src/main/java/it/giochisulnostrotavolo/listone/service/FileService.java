package it.giochisulnostrotavolo.listone.service;

public interface FileService {

	public String getRootDir();

	public String getRistaurantDir(Long idRest);

	public String getRestImageImgDir(Long idRest);

	public String getRestAreaImgDir(Long idRest, Long idArea);

	public String getRestCategoriesImgDir();

	public String getRestNewsImgDirByLang(Long idRest, String LangCode);

	public String getRestPdfDir(Long idRest, String langCode);

	public String getProductDir(String code);

}