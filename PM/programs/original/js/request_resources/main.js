/**
 * Returns a rendered component based on the room context
 * @param {String} resource the resource name to load
 * @param {String} roomId the id of the room
 * @param {String} playerId the id of the player
 * @returns the server side rendered component
 */
function requestResource(resource, roomId, username) {
    const http = new XMLHttpRequest();
    http.open("GET", `/resource?resource=${resource}&roomId=${roomId}&username=${username}`, false);
    http.send();

    if (http.status === 200) return http.responseText;
    return null;
}


document.addEventListener("DOMContentLoaded", () => {

    const username = new URLSearchParams(window.location.search).get("username");
    const roomId = window.location.pathname.slice(6);


    const loadGameButtonEl = document.querySelector("#loadGameButton");

    loadGameButtonEl.addEventListener("click", (e) => {
        e.preventDefault();

        const mainEl = document.querySelector("main");

        mainEl.innerHTML = requestResource("gameContent", roomId, username);
    });

});
