import logging

from flask import Flask, redirect, request, abort
from flask.logging import default_handler

from utils import RoomData

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
room_data = RoomData()


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

    room_id = room_data.generate_room()

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
    if room_data.get(room_id)["playerCount"] == 2:
        abort(403)

    # add the player to the room
    room_data.add_player(room_id)

    return {"data": f"Game room {room_id} | username: {username}", "status": 200}
