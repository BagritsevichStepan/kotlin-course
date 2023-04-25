package csc.makrobot.exception

open class InitializeException(notInitializedPart: String, parentPart: String):
    Exception("Part $notInitializedPart of the $parentPart must be initialized.")