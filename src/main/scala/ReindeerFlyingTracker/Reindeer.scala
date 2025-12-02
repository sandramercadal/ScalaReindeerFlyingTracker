/** Scala book chapter 4
 Incorporating: 1.Case class, 2.Trait, 3.Private val, 4.Companion object, 5.
 **/

package ReindeerFlyingTracker

case class HasRedNose(value: Boolean)
case class CanFlyMagically (value: Boolean)
case class FlightDistance (value: Double) //Km
case class FlightSpeed(value: Double) //Km per hour
case class HoursFlown(value: Int) //Total flown
case class FitnessLevel(value: Int) //1-10

trait MagicalFlyer {
  def canFlyMagically: Boolean

  def takeOff(): String = {
    if (canFlyMagically) "Taking off into the night sky tonight!"
    else "Sorry no flying for me, I must stay at the North Pole tonight."
  }
}

/**Immutable blueprint base Reindeer class **/
class Reindeer(val name: String,
               val age: Int,
               private val canFlyMagically: CanFlyMagically) { //Private field, noone outside of this class can access it

  /** Public method that reads the private data canFlyMagically (encapsulation) */
  def canFly: Boolean = canFlyMagically.value

  def aboutReindeer(): String =
    s"Hi!! I'm $name, a $age yr old reindeer!." +
    s"Can I can can fly magically? $canFlyMagically"
}

/**Companion Object - serves as a factory **/
object Reindeer {
  def createReindeer(name: String,
                     age: Int,
                     canFlyMagically: CanFlyMagically) : Reindeer = {
    new Reindeer(name, age, canFlyMagically)
  }
}


