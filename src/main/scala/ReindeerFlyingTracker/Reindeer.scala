/** Scala book chapter 4
 Incorporating: 1.Case class, 2.Trait, 3.Private val, 4.Companion object,
 5. Inheritance (extends with) 6. Methods with side effects  7. App trait extends App
 **/

package ReindeerFlyingTracker

case class HasRedNose(value: Boolean)
case class CanFlyMagically (value: Boolean)
case class FlightDistance (value: Double) //Km
case class FlightSpeed(value: Double) //Km per hour
case class HoursFlown(value: Int) //Total hours flown
case class FitnessLevel(value: Int) //1-10

trait MagicalFlyer {
  def canFlyMagically: Boolean

  def takeOff(): String = {
    if (canFlyMagically) "Taking off into the Christmas night sky tonight!"
    else "Sorry no flying for me, I must stay at the North Pole tonight."
  }
}

/** [BASE CLASS] Immutable blueprint Reindeer class **/
class Reindeer(val name: String,
               val age: Int,
               private val canFlyMagically: CanFlyMagically) { //Private field, noone outside of this class can access it

  /** Public method that reads the private data canFlyMagically (encapsulation) */
  def canFly: Boolean = canFlyMagically.value

  def aboutReindeer(): String =
    s"Hi!! I'm $name, a $age yr old reindeer!." +
    s"Can I fly magically? $canFlyMagically"
} //class closure

/**[COMPANION OBJ for BASE CLASS] Companion Objfor Reindeer - serves as a factory **/
object Reindeer {
  def createReindeer(name: String,
                     age: Int,
                     canFlyMagically: CanFlyMagically) : Reindeer = {
    new Reindeer(name, age, canFlyMagically)
  }
} //companion object closure

/** [JUNIOR REINDEER] */
class JuniorReindeer (name: String,
                      age: Int,
                      canFlyMagically: CanFlyMagically,
                      val healthCheck: Int, // 0-100%, needs to be at >70% to fly
                      private val fitnessLevel: FitnessLevel)
  extends Reindeer (name, age, canFlyMagically) with MagicalFlyer {

  // Companion object for JuniorReindeer
  object JuniorReindeer {
    def createJuniorReindeer(name: String,
                             age: Int,
                             canFlyMagically: CanFlyMagically,
                             healthCheck: Int,
                             fitnessLevel: FitnessLevel): JuniorReindeer = {
      new JuniorReindeer(name, age, canFlyMagically, healthCheck, fitnessLevel)
    }

    // Override trait method

    // Public method to read private encapsulation

    // Check who can fly tonight

    // Companion object for JuniorReindeer

  } //JuniorReindeer class closure

  object JuniorReindeerTrackerApp extends App {
    // Your code runs here!
  }