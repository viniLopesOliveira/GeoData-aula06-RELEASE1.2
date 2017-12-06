package br.usjt.desmob.geodata;

import java.io.Serializable;
import java.util.ArrayList;
import java.text.Collator;

/**
 * Created by asbonato on 01/09/17.
 */

public class Pais implements Serializable, Comparable {
    private String nome; // ok
    private String codigo3; // ok
    private String capital; // ok
    private String regiao; // ok
    private String subRegiao; // ok
    private String demonimo; // ok
    private int populacao; // ok
    private int area; // ok
    private String bandeira; // ok
    private double gini; // ok
    private ArrayList<String> idiomas; // ok
    private ArrayList<String> moedas; // ok
    private ArrayList<String> dominios; // ok
    private ArrayList<String> fusos; // ok
    private ArrayList<String> fronteiras;
    private double latitude; // ok
    private double longitude; // ok

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo3() {
        return codigo3;
    }

    public void setCodigo3(String codigo3) {
        this.codigo3 = codigo3;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getSubRegiao() {
        return subRegiao;
    }

    public void setSubRegiao(String subRegiao) {
        this.subRegiao = subRegiao;
    }

    public String getDemonimo() {
        return demonimo;
    }

    public void setDemonimo(String demonimo) {
        this.demonimo = demonimo;
    }

    public int getPopulacao() {
        return populacao;
    }

    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public double getGini() {
        return gini;
    }

    public void setGini(double gini) {
        this.gini = gini;
    }

    public ArrayList<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(ArrayList<String> idiomas) {
        this.idiomas = idiomas;
    }

    public ArrayList<String> getMoedas() {
        return moedas;
    }

    public void setMoedas(ArrayList<String> moedas) {
        this.moedas = moedas;
    }

    public ArrayList<String> getDominios() {
        return dominios;
    }

    public void setDominios(ArrayList<String> dominios) {
        this.dominios = dominios;
    }

    public ArrayList<String> getFusos() {
        return fusos;
    }

    public void setFusos(ArrayList<String> fusos) {
        this.fusos = fusos;
    }

    public ArrayList<String> getFronteiras() {
        return fronteiras;
    }

    public void setFronteiras(ArrayList<String> fronteiras) {
        this.fronteiras = fronteiras;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "\nnome='" + nome + '\'' +
                "\ncodigo3='" + codigo3 + '\'' +
                "\ncapital='" + capital + '\'' +
                "\nregiao='" + regiao + '\'' +
                "\nsubRegiao='" + subRegiao + '\'' +
                "\ndemonimo='" + demonimo + '\'' +
                "\npopulacao=" + populacao +
                "\narea=" + area +
                "\nbandeira='" + bandeira + '\'' +
                "\ngini=" + gini +
                "\nidiomas=" + idiomas +
                "\nmoedas=" + moedas +
                "\ndominios=" + dominios +
                "\nfusos=" + fusos +
                "\nfronteiras=" + fronteiras +
                "\nlatitude=" + latitude +
                "\nlongitude=" + longitude +
                "\n}";
    }

    @Override
    public int compareTo(Object o) {
        if (o == null || o.getClass() != getClass()) {
            return 0;
        } else {
            /* java.text.Collator API 1.5
             * A classe Collator executa comparacao de sequencia de caracteres sensivel a localidade.
    	     * Voce usa essa classe para criar rotinas de busca e classificacao de texto em linguagem
    	     * natural.
    	     * Referencias:
    	     * https://docs.oracle.com/javase/7/docs/api/java/text/Collator.html
    	     * http://stackoverflow.com/questions/12889760/sort-list-of-strings-with-localization
    	     * */
            Pais pais = (Pais) o;
            // Collator e uma classe abstrata. Utilize o seu factory para instanciar.
            Collator c = Collator.getInstance();
            // A atribuicao de pontos fortes aos recursos de linguagem depende da regiao.
            c.setStrength(Collator.PRIMARY);
            return c.compare(this.nome, pais.getNome());
            //usar o compareTo nao ordena corretamente caracteres acentuados
            //return getNome().compareTo(pais.getNome());
        }
    }
}
