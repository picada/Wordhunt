# Wordhunt

Ohjelmistotekniikan menetelmät -kurssiin harjoitustyönä toteutettava sovellus. Kyseessä on sananetsintäpeli, jossa ideana on etsiä sovelluksen satunnaisesti asettelemasta kirjainpelilaudasta suomenkielisiä sanoja annetun ajan puitteissa. 

## Dokumentaatio


[Vaatimusmäärittely](https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/tuntikirjanpito.md)

[Arkkitehtuurikuvaus (alustava luokkakaavio)](https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/arkkitehtuuri.md)


### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_


### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_



Sisältöä päivitetään kurssin edetessä.
