# Spring Rest API with Keycloak integration

## Goal
To demo how to set up a Spring Rest API with Keycloak integration

## Steps

### Keycloak

>docker pull jboss/keycloak

> docker run -p 8190:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin --name keycloak jboss/keycloak

Port 8190 will be how we access Keycloak

1. Create a Realm  <MyOwnRealm>
2. Create a Client <BasicRestAPIClient>
3. Create a Role <BasicUser>
4. Create a Group <StandardUsers>
5. Create a User <tobias>
   
   5.1 username: tobias
   
   5.2 password: abc123

   5.3 Save it

   5.4 Go back into the user and ensure that "Requierd User Action" is empty

### Settings in the Rest API Application

Now edit the application.properties file

* Note that you set these parameters properly
* See how we clearly specify which roles should be associated with which path (e.g. /api/message)

```

keycloak.auth-server-url=http://localhost:8190/auth
keycloak.realm=MyOwnRealm
keycloak.resource=BasicRestAPIClient
keycloak.public-client=true
keycloak.principal-attribute=given_name

keycloak.security-constraints[0].authRoles[0]=BasicUser
keycloak.security-constraints[0].securityCollections[0].patterns[0]=/api/message
```


### Aquire an Access Token

>POST http://localhost:#PORT#/auth/realms/#REALM-NAME#/protocol/openid-connect/token

Use "x-www-form-urlencoded"

<table><tr><td>Key</td><td>Value</td></tr>
<tr><td>client_id</td><td>BasicRestAPIClient</td></tr>
<tr><td>username</td><td>tobias</td></tr>
<tr><td>password</td><td>abc123</td></tr>
<tr><td>grant_type</td><td>password</td></tr>
<tr><td></td><td></td></tr>
</table>

e.g.
>POST http://localhost:8190/auth/realms/MyOwnRealm/protocol/openid-connect/token

If successful then you should get something like this back

```
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIzRXZYV3NiU2VkUEl0c3lhYUFoZXIzaVVPa1JyX2NoaDFUd043S0lGbXNrIn0.eyJleHAiOjE2MzcyNDExODQsImlhdCI6MTYzNzI0MDg4NCwianRpIjoiMjNmYjU0NWEtYTVjYS00ZmE0LWJmYzItOGE5NzEwM2EwMjc5IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MTkwL2F1dGgvcmVhbG1zL015T3duUmVhbG0iLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiZmNlNGI5NGEtZjc0NC00ZjEyLTk5NzYtN2IxYzEyNGM1ZGE5IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiQmFzaWNSZXN0QVBJQ2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6ImEyZGY5N2I5LWI4MzYtNGFmMy1iOTczLTkxMmI4Y2EyZjZiNSIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovL3d3dy5hYmMxMjMuY29tIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJCYXNpY1VzZXIiLCJkZWZhdWx0LXJvbGVzLW15b3ducmVhbG0iLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwic2lkIjoiYTJkZjk3YjktYjgzNi00YWYzLWI5NzMtOTEyYjhjYTJmNmI1IiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoiVG9iaWFzIEVyaWtzc29uIiwicHJlZmVycmVkX3VzZXJuYW1lIjoidG9iaWFzIiwiZ2l2ZW5fbmFtZSI6IlRvYmlhcyIsImZhbWlseV9uYW1lIjoiRXJpa3Nzb24iLCJlbWFpbCI6InRvYmlhc0B0c29mdC5zZSJ9.l8_vlL_fxTjyeLoRUlLqdQhLKerr0BrAHq6pBNLw1CidNeAttRCm-Ts4imdudJUYIPNiuAIdoiFUcMtsHBYY8BW-_v0gmNp-pwbhyr5N_ipXYuiVLvI6KcA1OGuvs1sf2KByvZYyIcGKkK5IGVrxH4xJIJWCkrarNhFcvNFqipuLEFBle7TmnbtSP-UrGNcFD068VedkD1oOuQXPO1rg7QCgHKElX82qLFREHK-qV_2Va3KDiDBVSHUJ88ToCxecwxQzwqYtJC7FVf1MsTqh1NTquRJPu8n_yiL59Y32nC0gGGpDKlDDsz0rqXuJURNmiWzgb4RakfJK0ut-4pNsbA",
    "expires_in": 300,
    "refresh_expires_in": 1800,
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJmODg0ZGNmZS00NzEwLTRiMzktODA4Ny04Y2E2ZjgxNzM4MDEifQ.eyJleHAiOjE2MzcyNDI2ODQsImlhdCI6MTYzNzI0MDg4NCwianRpIjoiYzVkNWUxMzItNGYwNi00NzUxLTkwMWQtNDRhYmJkNjJhNjY4IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MTkwL2F1dGgvcmVhbG1zL015T3duUmVhbG0iLCJhdWQiOiJodHRwOi8vbG9jYWxob3N0OjgxOTAvYXV0aC9yZWFsbXMvTXlPd25SZWFsbSIsInN1YiI6ImZjZTRiOTRhLWY3NDQtNGYxMi05OTc2LTdiMWMxMjRjNWRhOSIsInR5cCI6IlJlZnJlc2giLCJhenAiOiJCYXNpY1Jlc3RBUElDbGllbnQiLCJzZXNzaW9uX3N0YXRlIjoiYTJkZjk3YjktYjgzNi00YWYzLWI5NzMtOTEyYjhjYTJmNmI1Iiwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwic2lkIjoiYTJkZjk3YjktYjgzNi00YWYzLWI5NzMtOTEyYjhjYTJmNmI1In0.iowuQ6QzIbBC_G7OvsATQkcWfK91ek5n5mn72Ij1uPY",
    "token_type": "Bearer",
    "not-before-policy": 0,
    "session_state": "a2df97b9-b836-4af3-b973-912b8ca2f6b5",
    "scope": "email profile"
}
```

You can take the access_token and decode it with e.g. https://jwt.io/
And you should get something like this

#### Header
```
{
  "alg": "RS256",
  "typ": "JWT",
  "kid": "3EvXWsbSedPItsyaaAher3iUOkRr_chh1TwN7KIFmsk"
}
```

#### Payload
```
{
  "exp": 1637241184,
  "iat": 1637240884,
  "jti": "23fb545a-a5ca-4fa4-bfc2-8a97103a0279",
  "iss": "http://localhost:8190/auth/realms/MyOwnRealm",
  "aud": "account",
  "sub": "fce4b94a-f744-4f12-9976-7b1c124c5da9",
  "typ": "Bearer",
  "azp": "BasicRestAPIClient",
  "session_state": "a2df97b9-b836-4af3-b973-912b8ca2f6b5",
  "acr": "1",
  "allowed-origins": [
    "http://www.abc123.com"
  ],
  "realm_access": {
    "roles": [
      "BasicUser",
      "default-roles-myownrealm",
      "offline_access",
      "uma_authorization"
    ]
  },
  "resource_access": {
    "account": {
      "roles": [
        "manage-account",
        "manage-account-links",
        "view-profile"
      ]
    }
  },
  "scope": "email profile",
  "sid": "a2df97b9-b836-4af3-b973-912b8ca2f6b5",
  "email_verified": false,
  "name": "Tobias Eriksson",
  "preferred_username": "tobias",
  "given_name": "Tobias",
  "family_name": "Eriksson",
  "email": "tobias@tsoft.se"
}
```

#### Signature
```
RSASHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  
{
  "e": "AQAB",
  "kty": "RSA",
  "n": "x-KOfucHDeYYIEGlsfQu2dafM2CBpt2mgbyQVeqwA4f2Geom1qbgk2910FoWO6zSysr6XSsfqbKW8mb_xPzpNY47mdhh8gLGmE4vQlW0AoQEv2kyhEEq51esS4dx-cM1guLb1mui61I4JFF6gDXx_OYOLi1Pxn74HOTI3ZMy8XtphGzLA9lRDFBCmzb0pIqAY5rpbSljh8PvF4OPr-BVVZ8Lj-M36COVSot1ofGYGx68ypuuvYicL_xKw8QbvtVgrLTii5dO1KYNPrcDh4Ap4fzJPAikCvoV5-8iCodiyfJttHlKaz2HpxepF2i9kwVGU3K1NEJ4eWiiJxvIAXPN-w"
}
,
  
Private Key in PKCS #8, PKCS #1, or JWK string format. The key never leaves your browser.

)
```

### Make call to API with Authorization

e.g.
>GET http://localhost:8082/api/message

Header
Authorization 'Bearer' + acccess-token-from-above

