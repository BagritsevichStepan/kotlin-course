# Makrobot DSL

The project contains the implementation of the Makrobot DSL using Kotlin DSL Development Technologie.

With the Makrobot DSL you can create your robot, defining its parts from head to chassis, and then create scripts for it.

## Links
1. [Robot Description](#robot-description)
2. [Scenario Description](#scenario-description)
3. [Destructuring Declaration](#destructuring-declaration)

## Robot Description
Using DSL you can create an `MakroBot` instance. For example:
```
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
```

The description of the robot must contain the `head`, `body`, `hands`, and `chassis` description. You can also give the robot a name.

For the head, body and arms, specify the `material` from which they are made and material thickness.

The `head` consists of eyes and a mouth. The eyes can be Led and Lamp. The mouth must contain speaker description.
```
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
```

To the `body` description inscriptions must be added. In inscription block use unary plus to add new inscription.

```
body {
    metal withThickness 1

    inscription {
        +"I don't want to survive."
        +"I want live."
    }
}
```

In the description of the hands you must specify the load they can withstand (using minus operator). Supported load levels (in ascending order):
`VeryLight`, `Light`, `Medium`, `Heavy`, `VeryHeavy`, `Enormous`.
```
hands {
    plastic withThickness 3
    load = Light - Heavy
}
```

Supported types of the `chassis`:
```
chassis = caterpillar width 10

chassis = legs

chassis = wheels {
    diameter = 4
    count = 2
}
```


## Scenario Description
Using DSL you can also create scripts for the robot. For example:
```
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
```

In scenario block you can specify speed and power of the robot instance, add operations go forward, backward, turn around, pronounce the text. You can also specify schedule of the scenario.

## Destructuring Declaration
`MakroBot` instance supports destructuring declaration:
```
operator fun MakroBot.component1() = name
operator fun MakroBot.component2() = speed
operator fun MakroBot.component3() = power
```
So you can use in your variables declaration:
```
val (name, speed) = robot
```

