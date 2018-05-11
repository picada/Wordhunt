# Wordhunt

Ohjelmistotekniikan menetelmät -kurssiin harjoitustyönä toteutettu sovellus. Kyseessä on sananetsintäpeli, jossa ideana on etsiä sovelluksen satunnaisesti asettelemasta kirjainpelilaudasta suomenkielisiä sanoja annetun ajan puitteissa. 

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/arkkitehtuuri.md)

[Käyttöohje](https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/kayttoohje.md)

[Testausdokumentti](https://github.com/picada/otm-harjoitustyo/blob/master/Wordhunt/dokumentointi/testausdokumentti.md)

## Releaset

[Viikko 5](https://github.com/picada/otm-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/picada/otm-harjoitustyo/releases/tag/viikko6)

[Loppupalautus](https://github.com/picada/otm-harjoitustyo/releases/tag/loppupalautus)

## Komentorivitoiminnot

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

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _wordhunt-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto target/site/apidocs/index.html
