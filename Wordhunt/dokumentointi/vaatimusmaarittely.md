# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on sananetsintäpeli, jossa ideana on etsiä sovelluksen satunnaisesti asettelemasta kirjainpelilaudasta suomenkielisiä sanoja annetun ajan puitteissa. Peli peilaa käyttäjän löytämiä sanoja tietokantaan, joka on koostettu Kotimaisten Kielten Keskuksen julkaisemasta nykysuomen sanalistasta.

Pelin pelaaminen vaatii käyttäjätunnuksen luonnin ja kirjautumisen. Sovellus tallentaa käyttäjien keräämät kokonaispistemäärät sekä korkeimmat yksittäisestä pelistä saadun pistemäärät, ja näyttää parhaiden pelaajien ranking-taulukon kaikille pelaajille. Sovellusta voi käyttää useampi rekisteröitynyt pelaaja, ja jokaisella pelaajalla on oma highscorensa. 

## Käyttäjät

Ainakin alkuvaiheessa sovelluksella on ainoastaan yksi käyttäjärooli eli _pelaaja_. 

## Käyttöliittymäluonnos

Tässä vaiheessa käyttöliittymäluonnosta ei ole vielä luotu. 

Tarkoituksena on, että sovellus aukeaa kirjautumisnäkymään, jossa käyttäjä voi joko kirjautua sisään tai luoda uuden pelitunnuksen.

Tämän jälkeen aukeaa näkymä, jossa käyttäjä voi joko aloittaa uuden pelin tai tarkastella omaa pelihistoriaansa.

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda itselleen pelaajatunnuksen
  - käyttäjätunnuksen täytyy olla uniikki ja pituudeltaan vähintään 4 merkkiä

- käyttäjä voi kirjautua järjestelmään
  - kirjautuminen onnistuu syötettäessä olemassa oleva käyttäjätunnus kirjautumislomakkeelle
  - jos syötettyä käyttäjää ei olemassa, järjestelmä antaa tästä ilmoituksen

### Kirjautumisen jälkeen

- käyttäjä voi tarkastella top-listoja ja omia maksimipisteitään
  - sovellus näyttää sekä käyttäjän parhaat pisteet että top-listan, joka muodostetaan kaikkein käyttäjien parhaista pisteistä
  
- käyttäjä voi tarkastella pelin sääntöjä

- käyttäjä voi aloittaa uuden pelin
  - alkuvaiheessa pelivariaatioita on vain yksi
  - pelin alkaessa järjestelmä luo pelaajalle uuden satunnaisen pelilaudan ja asettaa pelikellon lähtötilanteeseen
  
- käyttäjä voi kerätä ruudukosta sanoja, jotka muodostuvat toistensa vieressä vaaka-, pysty tai vinosuunnassa olevista sanaruuduista (sanojen ei siis tarvitse muodostaa suoraa linjaa)
  - sanan tulee olla perusmuodossa ja löytyä sovelluksen hyödyntämästä nykysuomen sanalistasta
  - hyväksyttyyn sanaan lukeutuvat kirjainruudut poistetaan laudalta ja korvataan uusilla satunnaisilla kirjaimilla

- käyttäjä saa pisteitä löytämistään sanoista
  - alkuvaiheessa pisteitä kertyy jokaisesta kirjaimesta saman verran, kehitysvaiheessa pisteytystä jalostetaan niin, että pidemmissä sanoissa pistekerroin per kirjain on suurempi

- käyttäjällä on käytössään x määrä sekoitusmahdollisuuksia, joilla ruudukon kirjaimet voi alustaa uudestaan
  - jokainen sekoitus kuitenkin vähentää pistesaldoa

- käyttäjä voi keskeyttää pelin halutessaan, jolloin tulokset eivät tallennu

- pelin loppuessa (=ajan kuluessa loppuun) pelin pisteet tallentuvat pelaajan pistesaldoon

- käyttäjä voi kirjautua ulos

## Toimintaympäristön rajoitteet

- ohjelmiston tulee toimia Linux- ja OSX-käyttöjärjestelmillä varustetuissa koneissa
- käyttäjien ja pelattujen pelien tiedot sekä sanatietokanta talletetaan paikallisen koneen levylle

## Jatkokehitysideoita

Perusversion jälkeen järjestelmää täydennetään voidaan täydentää esim. seuraavilla toiminnallisuuksilla

- käyttäjä voi lähettää pelihaasteen toiselle käyttäjälle sovelluksen kautta
- useampia pelivariaatioita (kesto, ruudukon koko, voidaan määrittää löydettävien sanojen määrä)
- jokaisen käyttäjän tulee määrittää itselleen salasana, joka syötetään kirjautuessa
- käyttäjätunnuksen poisto
- käyttäjä voi nähdä pisimmän löytämänsä sanan (kaikkien pelattujen pelien joukosta)
- käyttäjä voi nähdä pisimmän löydetyn sanan kaikkien pelaajien ja pelattujen pelien joukosta
- sovellukseen voidaan lisätä myös suuremmilla oikeuksilla varustettuja pääkäyttäjiä, joilla oikeus hallinnoida muita käyttäjätilejä ja esim. lisätä uusia sanoja tietokantaan
- pelin laajentaminen useammille eri kielille


