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

## Toiminnallisuudet

Seuraavassa on kuvattu sovelluksen toimintalogiikkaa joidenkin päätoiminnallisuuksien osalta sekvenssikaavioina.

### Käyttäjän kirjautuminen

Kun kirjautumisnäkymässä on syötekenttään kirjoitettu käyttäjätunnus ja klikataan painiketta "Kirjaudu sisään" etenee sovelluksen kontrolli seuraavasti (sekvenssikaaviossa ei ole kuvattu yhteyttä tietokantaan):

<img src="https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/login.png">

Painikkeen painamiseen reagoiva tapahtumankäsittelijä kutsuu sovelluslogiikan wordhuntService metodia login antaen parametriksi kirjautuneen käyttäjätunnuksen. Sovelluslogiikka selvittää userDao:n avulla onko käyttäjätunnus olemassa. Jos on, eli kirjautuminen onnistuu, on seurauksena se että käyttöliittymä vaihtaa näkymäksi mainScenen, eli sovelluksen varsinaisen päänäkymän.

### Sovelluksen toiminta käyttäjän syöttäessä pelin aikana uuden, hyväksytyn sanan 

Kun käyttäjä klikkaa pelinäkymässä pelin ollessa käynnissä painiketta "Syötä sana" etenee sovelluksen kontrolli seuraavasti:

<img src="https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/sendWord2.png">

Tapahtumakäsittelijä kutsuu käyttöliittymän apuluokan UiHelp metodia acceptedWord, joka saa parametrikseen tämän hetkisen pelin, pelissä tällä hetkellä kerättynä olevan sanan sekä käyttöliittymän Labelit valid ja points. Metodi tarkistaa onko löytyykö syötetty sana uusi ja löytyykö se käynnissä olevan pelin sanalistasta. Mikäli sana on hyväksytty, päivitetään pelin pistesaldo ja palautetaan käyttöliittymään true sekä siirretään päivitetyt tekstit labeleihin. Tämän jälkeen kutsutaan metodeja insertNewLetters ja updatePuzzle, jotka vastaavat peliruudukon päivittämisestä - ensimmäinen noutaa jälleen käynnissä olevan pelin ja sen pelilaudan sekä arpoo uudet kirjaimet sanassa sanassa valittuina olleiden tilalle, jälkimmäinen puolestaan päivittää käyttöliittymän puzzle-gridPanen vastaamaan uutta peliruudukkoa. Lopputuloksena käyttäjälle näkyy päivitetty puzzleScene.

### Muut toiminnallisuudet

Sama periaate toistuu sovelluksen kaikissa toiminnallisuuksissa, käyttöliittymän tapahtumakäsittelijä kutsuu joko suoraan tai käyttöliittymän apuluokan kautta sopivaa sovelluslogiikan metodia ja sovelluslogiikka pelin / käyttäjän / näkymien tilaa. Kontrollin palatessa käyttöliittymään päivitetään tarvittaessa käyttäjän näkymä.



