# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:

<img src="https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/pakkaukset.png">

Pakkaus wordhunt.ui sisältää JavaFX:llä toteutetun käyttöliittymän, wordhunt.domain sovelluslogiikan ja wordhunt.dao tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä sisältää kuusi erillistä näkymää:

* Kirjautuminen
* Uuden käyttäjän luonti
* Päävalikko
* Pelinäkymä
* Huipputulokset
* Säännöt

Jokainen näistä on toteutettun omana Scene-olionaan. Näkymistä yksi kerrallaan on sijoitettuna sovelluksen stageen. Käyttöliittymä on rakennettu ohjelmallisesti luokassa wordhunt.ui.WordhuntUi, lisäki luokka hyödyntää apuluokkaa wordhunt.ui.UiHelp.

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat User, Score, Game ja Board, jotka kuvaavat pelin käyttäjiä, tuloksia ja pelejä sekä pelien aikana käytössä olevaa pelilautaa.

Toiminnallisista kokonaisuuksista vastaa luokka WordhuntService ja siitä käyttöliittymässä luotava ainoa olio, ja luokka vastaa mm. käyttöliittymän kirjautumiseen, uuden käyttäjän luontiin, uuden pelin luontiin sekä tulosten tallentamiseen ja tulostamiseen littyvistä toiminnoista. Luokan metodeja ovat mm. 

* boolean createUser(String username, String name)
* boolean login(String username)
* void setGame(int width, int height, String wordlist)
* boolean createScore(int points, User user, LocalDate date)
* String printTopTen()

### Ohjelman rakennetta kuvaava luokka-/pakkauskaavio:

<img src="https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/luokkakaavio.jpg">

## Tiedon pysyväistallennus

Pakkauksen wordhunt.database rajapintaa Dao ilmentävät luokat UserDao ja ScoreDao vastaavat tiedon tallennuksesta SQL-tietokantaan, joka luodaan luokan Database avulla.

## Päätoiminnallisuudet

Seuraavassa on kuvattu sovelluksen toimintalogiikkaa joidenkin päätoiminnallisuuksien osalta sekvenssikaavioina.

### Sovelluksen toiminta käyttäjän syöttäessä pelin aikana uuden, hyväksytyn sanan (päivitettävä)

<img src="https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/index.php.png">

pisteiden tallennus tietokantaan

käyttäjän kirjautuminen?

## Ohjelman rakenteeseen jääneet heikkoudet
