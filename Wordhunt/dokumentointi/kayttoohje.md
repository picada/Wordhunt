# Alustava käyttöohje

Lataa tiedosto [wordhunt.jar](https://github.com/picada/otm-harjoitustyo/releases/download/viikko6/wordhunt.jar)

## Ohjelman käynnistäminen 

Ohjelman olettaa, että käyttäjän tietokoneelle on asennettu SQLite. Muuta konfiguraatiota ei tarvita, pelin hyödyntämä sanalisa sekä säännöt on upotettu mukaan jar-tiedostoon.

## Ohjelman käynnistäminen 

Ohjelma käynnistetään komennolla java -jar wordhunt.jar

## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään:

KUVA

Kirjautuminen onnistuu kirjoittamalla olemassaoleva käyttäjätunnus syötekenttään ja painamalla "Kirjaudu sisään".

## Uuden käyttäjän luominen

Kirjautumisnäkymästä on mahdollista siirtyä uuden käyttäjän luomisnäkymään panikkeella "Luo uusi käyttäjä".

Uusi käyttäjä luodaan syöttämällä tiedot syötekenttiin ja painamalla "Luo".

KUVA

Jos käyttäjän luominen onnistuu, palataan kirjautumisnäkymään.

## Päävalikko

Onnistumisen kirjautumisen jälkeen avautuu päänäkymä, josta käyttäjä voi joko siirtyä lukemaan pelin säännöst, tarkastelemaan tuloksia tai pelinäkymään. Käyttäjä voi myös kirjautua ulos päänäkymän kautta.

KUVA


## Säännöt

Tästä näkymästä löytyvät pelin säännöt.

KUVA


## Tulokset

Tulosnäkymässä käyttäjä voi tarkastella sekä omia huipputuloksiaan sekä kaikkien käyttäjien tuloksista koottua top-listaa. Molemmat listat näyttävät maksimissaan kymmenen parasta tulosta. 

KUVA

## Pelinäkymä

Pelinäkymään siirryttyään käyttäjä voi aloittaa uuden pelin painamalla Aloita peli -nappia, minkä jälkeen pelikello käynnistyy ja pelilauta aktivoituu. 

Sanoja kerätään valitsemalla kirjaimet ruudukosta hiirivalinnalla yksi kirjain kerrallaan. Seuraavan kirjaimen tulee sijaita edellisen valitun ruudun vieressä joko pysty-, vaaka- tai vinosuunnassa. Sana päivittyy reaaliajassa sivun oikeaan laitaan, ja sanan voi syöttää 
järjestelmään painamalla ”Syötä sana”-nappia.

KUVA

Käyttäjän klikattua "Syötä sana" -nappia sovellus tarkistaa, löytyykö kyseinen sana sovellukseen ajetusta sanalistasta. Mikäli sana löytyy, pelaajan pistesaldo kasvaa ja valittujen kirjainten tilalle arvotaan uudet satunnaiset kirjaimet. Mikäli sana ei ole hyväksytty, ohjelma ilmoittaa tästä erikseen.

KUVA?

Käyttäjä voi koska tahansa tyhjentää valintansa painamalla "Tyhjennä valinnat".

Käyttäjä voi missä tahansa vaiheessa peliä sekoittaa pelilaudan, mutta tämä vaikuttaa negatiivisesti pistesaldoon. Pistesaldo ei voi kuitenkaan mennä miinukselle.

Kun pelikellon aika loppuu, tallentuvat pelin pisteet automaattisesti tietokantaan eikä käyttäjä pysty enää valitsemaan uusia sanoja. Pelaaja voi aloittaa uuden pelin päävalikon kautta. 

Mikäli käyttäjä poistuu pelistä kesken pelin, tulokset eivät tallennu tietokantaan ja peli nollautuu. 


