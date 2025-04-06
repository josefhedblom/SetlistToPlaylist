# Setlist to Playlist

En minimalistisk webapp för att snabbt hitta setlists från konserter med dina favoritartister – och som snart även kommar att kunna omvandla dem till spellistor direkt i Spotify eller Apple Music.

## Bakgrund

Jag använder ofta [setlist.fm](https://www.setlist.fm) för att kolla vilka låtar ett band eller en artist spelat på sina konserter. Även om sidan är informativ, tycker jag ibland att det är lite rörigt – speciellt när man snabbt vill hitta en specifik konsert eller setlist. Därför byggde jag **Setlist to Playlist** med fokus på en snabb, enkel och ren användarupplevelse där man kan:

- Söka efter en artist
- Få upp en lista på konserter
- Se setlisten direkt – utan att klicka runt

## Teknologi

Projektet är uppdelat i två delar:

### Backend
- **Spring Boot** – REST API för att hämta data från [Setlist.fm API](https://api.setlist.fm/docs/).
- API-proxy för att förenkla kommunikation mellan frontend och backend, särskilt när appen körs på en server.

### Frontend
- **React** – Byggt med [Vite.js](https://vitejs.dev) för snabb utveckling och modern tooling.
- Minimalistisk design – fokus på snabb interaktion och tydlighet.

## Funktionalitet

- Sök på en artist
- Visa konserter
- Se låtlistan från en specifik konsert

## På gång (kommande features)

Jag jobbar just nu på två funktioner som jag själv saknar på setlist.fm:

1. **Spela låtar direkt i Spotify eller Apple Music**  
   ➝ Möjlighet att klicka på en låt och öppna den direkt i din streamingtjänst

2. **Skapa spellistor baserat på setlists**  
   ➝ Generera en spellista från en konsert och spara den i ditt Spotify- eller Apple Music-konto
