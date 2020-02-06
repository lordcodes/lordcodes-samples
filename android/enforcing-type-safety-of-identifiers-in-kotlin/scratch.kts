/// Copy and paste the contents of this file into a scratch file in Android Studio or IntelliJ to try it out.


import java.util.UUID

// General types

data class Team(val id: UUID, val size: Int)
data class TeamMember(val id: String, val name: String)

// Wrapper type

data class WrapperIdentifier(val rawValue: UUID)

data class GenericIdentifier<RawT>(val rawValue: RawT)


// Ensuring the correct entity

data class Identifier<EntityT, RawT>(
    val rawValue: RawT
)

typealias RoomId = Identifier<Room, UUID>
data class Room(val id: RoomId)

typealias MeetingId = Identifier<Meeting, UUID>
data class Meeting(val id: MeetingId)

val room = Room(RoomId(UUID.randomUUID()))
val meeting = Meeting(MeetingId(UUID.randomUUID()))

fun bookMeeting(id: MeetingId) {}

//bookMeeting(room.id)
bookMeeting(meeting.id)