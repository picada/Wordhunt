# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Pakkauksen wordhunt.domain luokkien muodostamaa sovelluslogiikkaa testaavat kokonaisvaltaisimmin integraatiotestit testiluokassa WordhuntServiceTest, jonka määrittelevät testitapaukset pyrkivät simuloimaan WordhuntService-olion avulla suoritettavia toiminnallisuuksia.

Integraatiotestit käyttävät datan pysyväistallennukseen testitietokantaa test.db, joka rakennetaan ja tyhjennetään testien yhteydessä.

Sovelluslogiikan keskiössä ovat myös luokat Game ja Board, ja molemmille luokille on pyritty testit mahdollisimman kattavasti. Lisäksi kerroksen luokille User ja Score on tehty muutama yksikkötesti.

### DAO-luokat

Molempien DAO-luokkien toiminnallisuutta testataan omissa testiluokissaan UserDaoTest ja ScoreDaoTest luomalla testeissä tilapäinen tietokanta test.db, joka rakennetaan ja tyhjennetään uudestaan jokaisen testin yhteydessä.

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 77% ja haarautumakattavuus 82%.

<img src="https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/testikattavuus.png">

Testaamatta ovat tällä hetkellä mm. tiedostoista lukemisesta vastaavat metodit sekä poikkeustilanteet ja top-listojen tulostustoiminnot.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi 

Sovellus on haettu ja sitä on testattu käyttöohjeen kuvaamalla tavalla sekä OSX- että Linux-ympäristöön.

Sovellusta on testattu sekä tilanteissa, joissa käyttäjät ja tulokset tallentava tietokanta on ollut olemassa ja joissa niitä ei ole ollut jolloin ohjelma on luonut tietokannan ensimmäisellä käynnistyskerralla. 

### Toiminnallisuudet

Kaikki määrittelydokumentin ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. 

## Sovellukseen jääneet laatuongelmat

Sovellus ei anna tällä hetkellä järkeviä virheilmoituksia valtaosassa tilanteita. 
