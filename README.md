# South System

## Development server
Run `mvn spring-boot:run` for a dev server. Navigate to `http://localhost:8080/api/v1/swagger-ui.html`.

## API versioning (Bônus 4)
- This Api is versioned in its first version via URL (/api/v1).
- This api could be versioned by adding a header to the Http request and informing the desired version

## Endpoints:

- Associated: /api/v1/associated
- Schedule: /api/v1/associated
- Voting Sesion: /api/v1/voting_session

# To Vote:
 -  Start a voting session (/api/v1/voting_session) stating a valid Schedule and the session time ("time": NULL if you do not want to inform the time)
 -  Vote in (/api/v1/schedule/vote) stating a valid Schedule and the session time ("time": NULL if you do not want to inform the time)
 - Register Vote informing AssociatedId, ScheduleId and Vote (true for 'Sim' or false for 'Não') during the Voting Session