from typing import TypedDict, Dict, List
import logging

from random import randint
from flask import Flask, redirect, request, abort
from flask.logging import default_handler


ROOM_ID_LEN = 5


class PlayerInfo(TypedDict):
    """Annotates information about a Player
    """
    username: str
    isReady: bool
    character: int
    position: List[int]
    facing: str
    isMoving: bool
    isInteracting: bool


class RoomInfo(TypedDict):
    """Annotates information about how the room info
    """
    playerCount: int
    players: Dict[str, PlayerInfo]


def generate_room(rooms):
    """Generates a valid and unique room id
    and creates a new room

    Returns:
        str: an unique room id
    """
    # atempt to generate an unique code
    room_id = ""
    while True:

        # generate a random string
        for _ in range(ROOM_ID_LEN):
            ascii_code = randint(ord("A"), ord("Z"))
            room_id += chr(ascii_code)

        if room_id not in rooms:
            break

        room_id = ""

    # create the room
    rooms[room_id] = dict(
        playerCount=0,
        players=dict()
    )

    return room_id


# create the app
app = Flask(
    __name__,
    static_url_path="",
    static_folder="static",
    template_folder="templates"
)
app.config["SECRET_KEY"] = "secret!"



# configure logger
gunicorn_error_logger = logging.getLogger('gunicorn.error')
app.logger.removeHandler(default_handler)
app.logger.handlers.extend(gunicorn_error_logger.handlers)
app.logger.setLevel(gunicorn_error_logger.level)

# Allocate space for the room data
room_data: Dict[str, RoomInfo] = dict()


# views

@app.route("/", methods=["GET"])
def home() -> dict:
    """Home page where the create room
    should be issued

    Returns:
        dict: a json response
    """
    # bad request
    if request.method != "GET":
        abort(400)

    return {"data": "Home page", "status": 200}



@app.route("/create-room", methods=["GET"])
def create_room():
    """Randomly creates a room id;
    opens a room on the server and redirects
    the user to it

    Returns:
        Response: redirects the user to the game room
    """

    # bad request
    if request.method != "GET":
        abort(400)

    args = request.args

    if "username" not in args:
        abort(400)

    username = args["username"]

    room_id = generate_room(room_data)

    app.logger.debug("Opened room '%s'", room_id)

    return redirect(f"/game/{room_id}?username={username}", code=303)


@app.route("/game/<room_id>", methods=["GET"])
def game(room_id: str) -> dict:
    """Connects the user to the room
    with the given id; If the room does not exist
    a 404 is raised

    Args:
        room_id (str): the room identifier (must exist in memory)

    Returns:
        dict: a json response
    """

    # bad request
    if request.method != "GET":
        abort(400)

    args = request.args

    # username not in params
    if "username" not in args:
        abort(400)

    username = args["username"]

    # room does not exist
    if room_id not in room_data:
        abort(404)

    # room is full
    if room_data[room_id]["playerCount"] == 2:
        abort(403)

    return {"data": f"Game room {room_id} | username: {username}", "status": 200}
