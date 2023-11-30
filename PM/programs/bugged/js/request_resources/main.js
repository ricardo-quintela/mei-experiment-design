/**
 * Returns a rendered component based on the room context
 * @param {String} resource the resource name to load
 * @param {String} roomId the id of the room
 * @param {String} playerId the id of the player
 * @returns the server side rendered component as a string
 * @example
 * requestResource("gameContent", roomId, username);
 * "<h1>Game Content!</h1>\n<p>You are playing the game!</p>"
 */
function requestResource(resource, roomId, username) {
    const http = new XMLHttpRequest();
    http.open("GET", `/resource?resource=${resource}&roomId=${roomId}&username=${username}`, false);
    http.send();

    if (http.status === 200) return http.responseText;
    return null;
}


document.addEventListener("DOMContentLoaded", () => {

    let mainEl;
    const username = new URLSearchParams(window.location.search).get("username");
    const roomId = window.location.pathname.slice(6);


    const loadGameButtonEl = document.querySelector("#loadGameButton");

    loadGameButtonEl.addEventListener("click", (e) => {
        e.preventDefault();

        // get the main element from the DOM
        document.querySelector(".main");

        // request the new content
        const resourceContent = requestResource("gameContent", roomId, username);

        // replaces the main element's inner html content with the new one
        if (mainEl != resourceContent) {
            mainEl.innerHtml = resourceContent.innerHtml;
        }
    });

});
