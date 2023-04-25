package csc.makrobot

import csc.makrobot.api.WeekDay.*
import csc.makrobot.api.parts.Load.*
import csc.makrobot.dsl.*
import csc.makrobot.dsl.initializer.robot

fun main() {
    val robot = robot("Wall-E") {
        head {
            plastic withThickness 2

            eyes {
                lampEyes {
                    count = 2
                    illumination = 10
                }
                ledEyes {
                    count = 1
                    illumination = 3
                }
            }

            mouth {
                speaker {
                    power = 3
                }
            }
        }

        body {
            metal withThickness 1

            inscription {
                +"I don't want to survive."
                +"I want live."
            }
        }

        hands {
            plastic withThickness 3
            load = Light - Heavy
        }

        chassis = caterpillar width 10
    }

    scenario {
        robot {
            speed = 2
            power = 4
        }

        robot forward 5
        robot back 2
        robot.turn()

        robot pronounce {
            +"I want to ride my bicycle"
            +"I want to ride it where I like"
        }

        schedule {
            repeat(Tue at 10, Sat at 12)
            except(13)
            repeat(Wed..Fri at 8)
        }
    }

    val (name, speed) = robot
}
