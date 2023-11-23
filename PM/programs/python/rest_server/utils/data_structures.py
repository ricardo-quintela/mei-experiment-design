from typing import TypedDict, Dict, List, Union

ROOM_ID_LEN = 5

class RoomInfo(TypedDict):
    """Annotates information about how the room info
    """
    playerCount: int
    players: List[str]

class RoomData:
    """Stores information about rooms
    """
    def __init__(self):
        self._rooms: RoomInfo = dict()

    def __contains__(self, value):
        return value in self._rooms

    def get(self, room_id: str) -> Union[RoomInfo, None]:
        """Returns the room data for the given
        room id

        Args:
            room_id (str): the room's id

        Returns:
            RoomInfo: the room data for the given room id
        """
        return self._rooms[room_id] if room_id in self._rooms else None
    
    def add_player(self, room_id: str) -> Union[int, None]:
        """Increases the player count on the room with the given
        id if it exists

        Args:
            room_id (str): the room's id

        Returns:
            Union[int, None]: the room's player count or None if the room
            doesn't exist
        """

        if room_id not in self._rooms:
            return

        self._rooms[room_id]["playerCount"] += 1

        return self._rooms[room_id]["playerCount"]


    def generate_room(self):
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

            if room_id not in self._rooms:
                break

            room_id = ""

        # create the room
        rooms[room_id] = dict(
            playerCount=0,
            players=dict()
        )

        return room_id
