# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on sananetsintäpeli, jossa ideana on etsiä sovelluksen satunnaisesti asettelemasta kirjainpelilaudasta suomenkielisiä sanoja annetun ajan puitteissa. Peli peilaa käyttäjän löytämiä sanoja listaan, joka on koostettu Kotimaisten Kielten Keskuksen julkaisemasta nykysuomen sanalistasta.

Pelin pelaaminen vaatii käyttäjätunnuksen luonnin ja kirjautumisen. Sovellus tallentaa käyttäjän tuloksen aina pelin päättyessa, ja käyttäjä voi tarkastella sovelluksen kautta sekä omia huipputuloksiaan että kaikkien pelaajien tuloksista koostettua ranking-listaa. Sovellusta voi käyttää useampi rekisteröitynyt pelaaja. 

## Käyttäjät

Ainakin alkuvaiheessa sovelluksella on ainoastaan yksi käyttäjärooli eli _pelaaja_. 

## Käyttöliittymä

Käyttöliittymä sisältää kuusi erillistä näkymää:

* Kirjautuminen
* Uuden käyttäjän luonti
* Päävalikko
* Pelinäkymä
* Huipputulokset
* Säännöt

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda itselleen pelaajatunnuksen
  - käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan 4-8 merkkiä

- käyttäjä voi kirjautua järjestelmään
  - kirjautuminen onnistuu syötettäessä olemassa oleva käyttäjätunnus kirjautumislomakkeelle
  - jos syötettyä käyttäjää ei olemassa, järjestelmä antaa tästä ilmoituksen

### Kirjautumisen jälkeen

- käyttäjä voi tarkastella huipputuloksia
  - sovellus näyttää sekä käyttäjän parhaat pisteet että top-listan, joka muodostetaan kaikkein käyttäjien parhaista pisteistä
  - molemmissa listoissa on maksimissaan 10 parasta pistetulosta
  
- käyttäjä voi tarkastella pelin sääntöjä

- käyttäjä voi aloittaa uuden pelin
  - alkuvaiheessa pelivariaatioita on vain yksi
  - pelin alkaessa järjestelmä luo pelaajalle uuden satunnaisen pelilaudan ja asettaa pelikellon lähtötilanteeseen
  
- käyttäjä voi kerätä ruudukosta sanoja, jotka muodostuvat toistensa vieressä vaaka-, pysty tai vinosuunnassa olevista sanaruuduista (sanojen ei siis tarvitse muodostaa suoraa linjaa)
  - sanan tulee olla perusmuodossa ja löytyä sovelluksen hyödyntämästä nykysuomen sanalistasta
  - hyväksyttyyn sanaan lukeutuvat kirjainruudut poistetaan laudalta ja korvataan uusilla satunnaisilla kirjaimilla

- käyttäjä saa pisteitä löytämistään sanoista
  - pistekerroin on sitä suurempi, mitä pidempi sana on, tällä hetkellä kertoimet ovat seuraavat: 
    - max 3 kirjainta: 30 pistettä / kirjain
    - 4 kirjainta: 50 pistettä / kirjain
    - 5 kirjainta: 75 pistettä / kirjain
    - 6 kirjainta tai enemmän: 100 pistettä / kirjain
  
- käyttäjällä on käytössään rajoittamaton määrä sekoitusmahdollisuuksia, joilla ruudukon kirjaimet voi alustaa uudestaan
  - jokainen sekoitus kuitenkin vähentää pistesaldoa

- käyttäjä voi keskeyttää pelin halutessaan, jolloin tulokset eivät tallennu

- pelin loppuessa (=ajan kuluessa loppuun) sovellus tallentaa pelaajan pisteet tietokantaan

- käyttäjä voi kirjautua ulos

## Toimintaympäristön rajoitteet

- ohjelmiston tulee toimia Linux- ja OSX-käyttöjärjestelmillä varustetuissa koneissa
- käyttäjien ja pelattujen pelien tiedot sekä sanatietokanta talletetaan paikallisen koneen levylle

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään voidaan täydentää esim. seuraavilla toiminnallisuuksilla:

- käyttäjä voi lähettää pelihaasteen toiselle käyttäjälle sovelluksen kautta
- useampia pelivariaatioita (kesto, ruudukon koko, voidaan määrittää löydettävien sanojen määrä)
- jokaisen käyttäjän tulee määrittää itselleen salasana, joka syötetään kirjautuessa
- käyttäjätunnuksen poisto
- käyttäjä voi nähdä pisimmän löytämänsä sanan (kaikkien pelattujen pelien joukosta)
- käyttäjä voi nähdä pisimmän löydetyn sanan kaikkien pelaajien ja pelattujen pelien joukosta
- sovellukseen voidaan lisätä myös suuremmilla oikeuksilla varustettuja pääkäyttäjiä, joilla oikeus hallinnoida muita käyttäjätilejä ja esim. lisätä uusia sanoja tietokantaan
- eri sijamuotojen lisääminen sanalistaan
- pelin laajentaminen useammille eri kielille


