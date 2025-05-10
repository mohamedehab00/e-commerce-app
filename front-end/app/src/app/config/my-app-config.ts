export default{
    oidc: {
        clientId: '0oaoms7pbjfhulWo25d7',
        issuer: 'https://dev-39658920.okta.com/oauth2/default',
        redirectUri: window.location.origin + '/login/callback',
        scopes: ['openid', 'profile', 'email']
    }
}
