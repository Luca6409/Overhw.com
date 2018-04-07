package com.overhw.counttown;

/**
 * Created by Luca Fabris on 15/02/2018.
 */

public class Appalto {
    private String idGara;
    private String nrLotto;
    private String codiceFiscaleStazioneAppaltante;
    private String denominazioneStazioneAppaltante;
    private String dataPubbBandoScp;
    private String tipoBando;
    private String tipoSettore;
    private String infrStrategica;
    private String oggetto;
    private String tipoIntervento;
    private String rup;
    private String luogoEsecuzione;
    private String cup;
    private String cig;
    private String importo;
    private String cpv;
    private String categoria;
    private String classe;
    private String impLavori;
    private String tipoProcedura;
    private String astaElettronica;
    private String forcella;
    private String appaltoRiservato;
    private String dataPubbGuue;
    private String dataPubbGuri;
    private String dataPubbAlbo;
    private String urlProfiloComm;
    private String numQuotidianiNaz;
    private String numQuotidianiLoc;
    private String procAccelerata;
    private String preinformazione;
    private String termineRidotto;
    private String terminePresDomOff;
    private String numGioTermEsec;
    private String fpDirPrelaz;
    private String fpModifProgPrel;
    private String fpAccettModProPrel;
    private String fpObblCostSocProg;
    private String fpDurataConces;
    private String fpNote;
    private String url;

    public Appalto(String tipoIntervento, String idGara, String luogoEsecuzione, String denominazioneStazioneAppaltante, String dataPubbBandoScp){
        this.tipoIntervento = tipoIntervento;
        this.idGara = idGara;
        this.luogoEsecuzione = luogoEsecuzione;
        this.denominazioneStazioneAppaltante = denominazioneStazioneAppaltante;
        this.dataPubbBandoScp = dataPubbBandoScp;
    }

    public Appalto(String[] datiAppalto){
        if(datiAppalto.length == 41) {
            this.idGara = datiAppalto[0];
            this.nrLotto = datiAppalto[1];
            this.codiceFiscaleStazioneAppaltante = datiAppalto[2];
            this.denominazioneStazioneAppaltante = datiAppalto[3];
            this.dataPubbBandoScp = datiAppalto[4];
            this.tipoBando = datiAppalto[5];
            this.tipoSettore = datiAppalto[6];
            this.infrStrategica = datiAppalto[7];
            this.oggetto = datiAppalto[8];
            this.tipoIntervento = datiAppalto[9];
            this.rup = datiAppalto[10];
            this.luogoEsecuzione = datiAppalto[11];
            this.cup = datiAppalto[12];
            this.cig = datiAppalto[13];
            this.importo = datiAppalto[14];
            this.cpv = datiAppalto[15];
            this.categoria = datiAppalto[16];
            this.classe = datiAppalto[17];
            this.impLavori = datiAppalto[18];
            this.tipoProcedura = datiAppalto[19];
            this.astaElettronica = datiAppalto[20];
            this.forcella = datiAppalto[21];
            this.appaltoRiservato = datiAppalto[22];
            this.dataPubbGuue = datiAppalto[23];
            this.dataPubbGuri = datiAppalto[24];
            this.dataPubbAlbo = datiAppalto[25];
            this.urlProfiloComm = datiAppalto[26];
            this.numQuotidianiNaz = datiAppalto[27];
            this.numQuotidianiLoc = datiAppalto[28];
            this.procAccelerata = datiAppalto[29];
            this.preinformazione = datiAppalto[30];
            this.termineRidotto = datiAppalto[31];
            this.terminePresDomOff = datiAppalto[32];
            this.numGioTermEsec = datiAppalto[33];
            this.fpDirPrelaz = datiAppalto[34];
            this.fpModifProgPrel = datiAppalto[35];
            this.fpAccettModProPrel = datiAppalto[36];
            this.fpObblCostSocProg = datiAppalto[37];
            this.fpDurataConces = datiAppalto[38];
            this.fpNote = datiAppalto[39];
            this.url = datiAppalto[40];
        }
    }

    public String getIdGara() {
        return idGara;
    }

    public void setIdGara(String idGara) {
        this.idGara = idGara;
    }

    public String getNrLotto() {
        return nrLotto;
    }

    public void setNrLotto(String nrLotto) {
        this.nrLotto = nrLotto;
    }

    public String getCodiceFiscaleStazioneAppaltante() {
        return codiceFiscaleStazioneAppaltante;
    }

    public void setCodiceFiscaleStazioneAppaltante(String codiceFiscaleStazioneAppaltante) {
        this.codiceFiscaleStazioneAppaltante = codiceFiscaleStazioneAppaltante;
    }

    public String getDenominazioneStazioneAppaltante() {
        return denominazioneStazioneAppaltante;
    }

    public void setDenominazioneStazioneAppaltante(String denominazioneStazioneAppaltante) {
        this.denominazioneStazioneAppaltante = denominazioneStazioneAppaltante;
    }

    public String getDataPubbBandoScp() {
        return dataPubbBandoScp;
    }

    public void setDataPubbBandoScp(String dataPubbBandoScp) {
        this.dataPubbBandoScp = dataPubbBandoScp;
    }

    public String getTipoBando() {
        return tipoBando;
    }

    public void setTipoBando(String tipoBando) {
        this.tipoBando = tipoBando;
    }

    public String getTipoSettore() {
        return tipoSettore;
    }

    public void setTipoSettore(String tipoSettore) {
        this.tipoSettore = tipoSettore;
    }

    public String getInfrStrategica() {
        return infrStrategica;
    }

    public void setInfrStrategica(String infrStrategica) {
        this.infrStrategica = infrStrategica;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public String getTipoIntervento() {
        return tipoIntervento;
    }

    public void setTipoIntervento(String tipoIntervento) {
        this.tipoIntervento = tipoIntervento;
    }

    public String getRup() {
        return rup;
    }

    public void setRup(String rup) {
        this.rup = rup;
    }

    public String getLuogoEsecuzione() {
        return luogoEsecuzione;
    }

    public void setLuogoEsecuzione(String luogoEsecuzione) {
        this.luogoEsecuzione = luogoEsecuzione;
    }

    public String getCup() {
        return cup;
    }

    public void setCup(String cup) {
        this.cup = cup;
    }

    public String getCig() {
        return cig;
    }

    public void setCig(String cig) {
        this.cig = cig;
    }

    public String getImporto() {
        return importo;
    }

    public void setImporto(String importo) {
        this.importo = importo;
    }

    public String getCpv() {
        return cpv;
    }

    public void setCpv(String cpv) {
        this.cpv = cpv;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getImpLavori() {
        return impLavori;
    }

    public void setImpLavori(String impLavori) {
        this.impLavori = impLavori;
    }

    public String getTipoProcedura() {
        return tipoProcedura;
    }

    public void setTipoProcedura(String tipoProcedura) {
        this.tipoProcedura = tipoProcedura;
    }

    public String getAstaElettronica() {
        return astaElettronica;
    }

    public void setAstaElettronica(String astaElettronica) {
        this.astaElettronica = astaElettronica;
    }

    public String getForcella() {
        return forcella;
    }

    public void setForcella(String forcella) {
        this.forcella = forcella;
    }

    public String getAppaltoRiservato() {
        return appaltoRiservato;
    }

    public void setAppaltoRiservato(String appaltoRiservato) {
        this.appaltoRiservato = appaltoRiservato;
    }

    public String getDataPubbGuue() {
        return dataPubbGuue;
    }

    public void setDataPubbGuue(String dataPubbGuue) {
        this.dataPubbGuue = dataPubbGuue;
    }

    public String getDataPubbGuri() {
        return dataPubbGuri;
    }

    public void setDataPubbGuri(String dataPubbGuri) {
        this.dataPubbGuri = dataPubbGuri;
    }

    public String getDataPubbAlbo() {
        return dataPubbAlbo;
    }

    public void setDataPubbAlbo(String dataPubbAlbo) {
        this.dataPubbAlbo = dataPubbAlbo;
    }

    public String getUrlProfiloComm() {
        return urlProfiloComm;
    }

    public void setUrlProfiloComm(String urlProfiloComm) {
        this.urlProfiloComm = urlProfiloComm;
    }

    public String getNumQuotidianiNaz() {
        return numQuotidianiNaz;
    }

    public void setNumQuotidianiNaz(String numQuotidianiNaz) {
        this.numQuotidianiNaz = numQuotidianiNaz;
    }

    public String getNumQuotidianiLoc() {
        return numQuotidianiLoc;
    }

    public void setNumQuotidianiLoc(String numQuotidianiLoc) {
        this.numQuotidianiLoc = numQuotidianiLoc;
    }

    public String getProcAccelerata() {
        return procAccelerata;
    }

    public void setProcAccelerata(String procAccelerata) {
        this.procAccelerata = procAccelerata;
    }

    public String getPreinformazione() {
        return preinformazione;
    }

    public void setPreinformazione(String preinformazione) {
        this.preinformazione = preinformazione;
    }

    public String getTermineRidotto() {
        return termineRidotto;
    }

    public void setTermineRidotto(String termineRidotto) {
        this.termineRidotto = termineRidotto;
    }

    public String getTerminePresDomOff() {
        return terminePresDomOff;
    }

    public void setTerminePresDomOff(String terminePresDomOff) {
        this.terminePresDomOff = terminePresDomOff;
    }

    public String getNumGioTermEsec() {
        return numGioTermEsec;
    }

    public void setNumGioTermEsec(String numGioTermEsec) {
        this.numGioTermEsec = numGioTermEsec;
    }

    public String getFpDirPrelaz() {
        return fpDirPrelaz;
    }

    public void setFpDirPrelaz(String fpDirPrelaz) {
        this.fpDirPrelaz = fpDirPrelaz;
    }

    public String getFpModifProgPrel() {
        return fpModifProgPrel;
    }

    public void setFpModifProgPrel(String fpModifProgPrel) {
        this.fpModifProgPrel = fpModifProgPrel;
    }

    public String getFpAccettModProPrel() {
        return fpAccettModProPrel;
    }

    public void setFpAccettModProPrel(String fpAccettModProPrel) {
        this.fpAccettModProPrel = fpAccettModProPrel;
    }

    public String getFpObblCostSocProg() {
        return fpObblCostSocProg;
    }

    public void setFpObblCostSocProg(String fpObblCostSocProg) {
        this.fpObblCostSocProg = fpObblCostSocProg;
    }

    public String getFpDurataConces() {
        return fpDurataConces;
    }

    public void setFpDurataConces(String fpDurataConces) {
        this.fpDurataConces = fpDurataConces;
    }

    public String getFpNote() {
        return fpNote;
    }

    public void setFpNote(String fpNote) {
        this.fpNote = fpNote;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
