package com.overhw.counttown;

/**
 * Created by LucaF on 22/01/2018.
 */

public class Comune {
    private String nome;
    private String istat;
    private String provincia;
    private String siglaProvincia;
    private String regione;
    private String areaGeo;
    private String popolazioneResidente;
    private String popolazioneStraniera;
    private String densitaDemografica;
    private String superficieKmq;
    private String altezzaCentro;
    private String altezzaMinima;
    private String altezzaMassima;
    private String zonaAltimetrica;
    private String tipoComune;
    private String gradoUrbanizzazione;
    private String indiceMontanita;
    private String zonaClimatica;
    private String zonaSismica;
    private String classeComune;
    private String latitudine;
    private String longitudine;

    public Comune(String[] details){
        this.nome = details[0];
        this.istat = details[1];
        this.provincia = details[2];
        this.siglaProvincia = details[3];
        this.regione = details[4];
        this.areaGeo = details[5];
        this.popolazioneResidente = details[6];
        this.popolazioneStraniera = details[7];
        this.densitaDemografica = details[8];
        this.superficieKmq = details[9];
        this.altezzaCentro = details[10];
        this.altezzaMinima = details[11];
        this.altezzaMassima = details[12];
        this.zonaAltimetrica = details[13];
        this.tipoComune = details[14];
        this.gradoUrbanizzazione = details[15];
        this.indiceMontanita = details[16];
        this.zonaClimatica = details[17];
        this.zonaSismica = details[18];
        this.classeComune = details[19];
        this.latitudine = details[20];
        this.longitudine = details[21];
    }

    public Comune(String nome, String istat, String provincia, String siglaProvincia, String regione, String areaGeo, String popolazioneResidente, String popolazioneStraniera, String densitaDemografica, String superficieKmq, String altezzaCentro, String altezzaMinima, String altezzaMassima, String zonaAltimetrica, String tipoComune, String gradoUrbanizzazione, String indiceMontanita, String zonaClimatica, String zonaSismica, String classeComune, String latitudine, String longitudine) {
        this.nome = nome;
        this.istat = istat;
        this.provincia = provincia;
        this.siglaProvincia = siglaProvincia;
        this.regione = regione;
        this.areaGeo = areaGeo;
        this.popolazioneResidente = popolazioneResidente;
        this.popolazioneStraniera = popolazioneStraniera;
        this.densitaDemografica = densitaDemografica;
        this.superficieKmq = superficieKmq;
        this.altezzaCentro = altezzaCentro;
        this.altezzaMinima = altezzaMinima;
        this.altezzaMassima = altezzaMassima;
        this.zonaAltimetrica = zonaAltimetrica;
        this.tipoComune = tipoComune;
        this.gradoUrbanizzazione = gradoUrbanizzazione;
        this.indiceMontanita = indiceMontanita;
        this.zonaClimatica = zonaClimatica;
        this.zonaSismica = zonaSismica;
        this.classeComune = classeComune;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getSiglaProvincia() {
        return siglaProvincia;
    }

    public void setSiglaProvincia(String siglaProvincia) {
        this.siglaProvincia = siglaProvincia;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getAreaGeo() {
        return areaGeo;
    }

    public void setAreaGeo(String areaGeo) {
        this.areaGeo = areaGeo;
    }

    public String getPopolazioneResidente() {
        return popolazioneResidente;
    }

    public void setPopolazioneResidente(String popolazioneResidente) {
        this.popolazioneResidente = popolazioneResidente;
    }

    public String getPopolazioneStraniera() {
        return popolazioneStraniera;
    }

    public void setPopolazioneStraniera(String popolazioneStraniera) {
        this.popolazioneStraniera = popolazioneStraniera;
    }

    public String getDensitaDemografica() {
        return densitaDemografica;
    }

    public void setDensitaDemografica(String densitaDemografica) {
        this.densitaDemografica = densitaDemografica;
    }

    public String getSuperficieKmq() {
        return superficieKmq;
    }

    public void setSuperficieKmq(String superficieKmq) {
        this.superficieKmq = superficieKmq;
    }

    public String getAltezzaCentro() {
        return altezzaCentro;
    }

    public void setAltezzaCentro(String altezzaCentro) {
        this.altezzaCentro = altezzaCentro;
    }

    public String getAltezzaMinima() {
        return altezzaMinima;
    }

    public void setAltezzaMinima(String altezzaMinima) {
        this.altezzaMinima = altezzaMinima;
    }

    public String getAltezzaMassima() {
        return altezzaMassima;
    }

    public void setAltezzaMassima(String altezzaMassima) {
        this.altezzaMassima = altezzaMassima;
    }

    public String getZonaAltimetrica() {
        return zonaAltimetrica;
    }

    public void setZonaAltimetrica(String zonaAltimetrica) {
        this.zonaAltimetrica = zonaAltimetrica;
    }

    public String getTipoComune() {
        return tipoComune;
    }

    public void setTipoComune(String tipoComune) {
        this.tipoComune = tipoComune;
    }

    public String getGradoUrbanizzazione() {
        return gradoUrbanizzazione;
    }

    public void setGradoUrbanizzazione(String gradoUrbanizzazione) {
        this.gradoUrbanizzazione = gradoUrbanizzazione;
    }

    public String getIndiceMontanita() {
        return indiceMontanita;
    }

    public void setIndiceMontanita(String indiceMontanita) {
        this.indiceMontanita = indiceMontanita;
    }

    public String getZonaClimatica() {
        return zonaClimatica;
    }

    public void setZonaClimatica(String zonaClimatica) {
        this.zonaClimatica = zonaClimatica;
    }

    public String getZonaSismica() {
        return zonaSismica;
    }

    public void setZonaSismica(String zonaSismica) {
        this.zonaSismica = zonaSismica;
    }

    public String getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }

    public String getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }

    public String getIstat() {
        return istat;
    }

    public void setIstat(String istat) {
        this.istat = istat;
    }

    public String getClasseComune() {
        return classeComune;
    }

    public void setClasseComune(String classeComune) {
        this.classeComune = classeComune;
    }
}
