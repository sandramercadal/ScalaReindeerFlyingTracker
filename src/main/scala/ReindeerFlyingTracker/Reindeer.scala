/** SCALA BOOK CHAPTER 4
 Incorporating: 1.Case class, 2.Trait, 3.Private val, 4.Companion object,
 5. Inheritance (extends with) 6. Methods with side effects  7. App trait extends App **/

package ReindeerFlyingTracker

import ReindeerFlyingTracker.EliteReindeer.SantaReport

case class HasRedNose(value: Boolean)
case class CanFlyMagically (value: Boolean)
case class FlightDistance (value: Double) //Km
case class FlightSpeed(value: Double) //Km per hour
case class HoursFlown(value: Int) //Total hours flown
case class FitnessLevel(value: Double) //1-10

trait MagicalFlyer {
  def canFlyMagically: Boolean  //trait method

  def reindeerTakeOff(): String = {
    if (canFlyMagically) "Taking off into the Christmas night sky tonight!" //Trait uses this method & returns boolean
    else "Oh no flying for me, I must stay at the North Pole tonight."
  }
}

/** [BASE CLASS] Immutable blueprint Reindeer class **/
class Reindeer(val name: String,
               val age: Int,
               private val canFlyMagically: CanFlyMagically) { //Private field, noone outside of this class can access it

  /** Public method that reads the private data [in trait MagicalFlyer] (encapsulation) */
  def canFly: Boolean = canFlyMagically.value

  def aboutReindeer(): String =
    s"Hi!! I'm $name, a $age yr old reindeer! " +
    s"Can I fly magically? $canFly!!"
} //base class closure

/**[COMPANION OBJ for BASE CLASS] for Reindeer - serves as a factory **/
object Reindeer {
  def createReindeer(name: String,
                     age: Int,
                     magicalAbility: CanFlyMagically) : Reindeer = {
    new Reindeer(name, age, magicalAbility)
  }
} //companion object closure


  /** [ J U N I O R  R E I N D E E R ] */
class JuniorReindeer (name: String,
                      age: Int,
                      magicalFlightAbility: CanFlyMagically,
                      val healthCheck: Int, // 0-100%, diff % required depending on type of reindeer
                      private val fitnessLevel: FitnessLevel) //Private field
  extends Reindeer (name, age, magicalFlightAbility) with MagicalFlyer {

    // Override trait method [needed by trait MagicalFlyer]
    override def canFlyMagically: Boolean = canFly //must be in class not comp obj

    //public method to access private field (private encapsulation)
    def juniorReindeerFitness: Double = fitnessLevel.value

   //key pattern matching to see who can fly tonight based on fitness, healthcheck & canFlyMagically
    def juniorReindeerFitToFly: Boolean = {
    healthCheck >= 68 && juniorReindeerFitness >= 8 && canFly
    }
}
  // Companion object for JuniorReindeer
  object JuniorReindeer {
    def createJuniorReindeer(name: String,
                             age: Int,
                             magicalFlightAbility: CanFlyMagically,
                             healthCheck: Int,
                             fitnessLevel: FitnessLevel): JuniorReindeer = {
      new JuniorReindeer(name, age, magicalFlightAbility, healthCheck, fitnessLevel)
    }
  }


/** [ E X P E R I E N C E D  R E I N D E E R ] */
class ExperiencedReindeer(name: String,
                       age: Int,
                       magicalFlightAbility: CanFlyMagically,
                       val healthCheck: Int,
                       private val fitnessLevel: FitnessLevel,
                       val hoursFlown: HoursFlown)
  extends Reindeer(name, age, magicalFlightAbility) with MagicalFlyer {

  override def canFlyMagically: Boolean = canFly

  // Public method to read private field (encapsulation!)
  def experiencedReindeerFitness: Double = fitnessLevel.value

  // experiencedReindeer pattern matching
  def experiencedReindeerFitToFly: Boolean = {
    healthCheck >= 65 && experiencedReindeerFitness >= 7.5 && canFly
  }
}

  object ExperiencedReindeer { // Companion object for ExperiencedReindeer
    def createExperiencedReindeer(name: String,
                                 age: Int,
                                 magicalFlightAbility: CanFlyMagically,
                                 healthCheck: Int,
                                 fitnessLevel: FitnessLevel,
                                 hoursFlown: HoursFlown): ExperiencedReindeer = {
      new ExperiencedReindeer(name, age, magicalFlightAbility, healthCheck, fitnessLevel, hoursFlown)
    }
  }


  /** [ E L I T E   R E I N D E E R ] */
  class EliteReindeer(name: String,
                      age: Int,
                      magicalFlightAbility: CanFlyMagically,
                      val healthCheck: Int,
                      private val fitnessLevel: FitnessLevel,
                      val hoursFlown: HoursFlown,
                      val hasRedNose: HasRedNose,
                      val specialAbility: String)
    extends Reindeer(name, age, magicalFlightAbility) with MagicalFlyer {

    override def canFlyMagically: Boolean = canFly

    // Public method to read private field (encapsulation!)
    def eliteReindeerFitness: Double = fitnessLevel.value

    // Pattern matching
    def eliteFitToFly: Boolean = {
      healthCheck >= 60 && eliteReindeerFitness >= 6.0 && canFly
    }
  }

    object EliteReindeer {
      def createEliteReindeer(name: String,
                              age: Int,
                              magicalFlightAbility: CanFlyMagically,
                              healthCheck: Int,
                              fitnessLevel: FitnessLevel,
                              hoursFlown: HoursFlown,
                              hasRedNose: HasRedNose,
                              specialAbility: String): EliteReindeer = {
        new EliteReindeer(name, age, magicalFlightAbility, healthCheck, fitnessLevel,
          hoursFlown, hasRedNose, specialAbility)
      }

      /** [ S A N T A  R E P O R T ] */
      object SantaReport {
        def generateReport(juniorTeam: List[JuniorReindeer],
                           experiencedTeam: List[ExperiencedReindeer],
                           eliteTeam: List[EliteReindeer]): Unit = {

          // Filter reindeer fit to fly status
          val juniorFlying = juniorTeam.filter(_.juniorReindeerFitToFly)
          val experiencedFlying = experiencedTeam.filter(_.experiencedReindeerFitToFly)
          val eliteFlying = eliteTeam.filter(_.eliteFitToFly)

          // Calculate total reindeers to fly
          val totalReindeer = juniorTeam.length + experiencedTeam.length + eliteTeam.length
          val totalCleared = juniorFlying.length + experiencedFlying.length + eliteFlying.length

          println("\n **********************************************")
          println("          SANTA'S REINDEER FLIGHT TRACKER")
          println(" **********************************************\n")

          println(s"   Total Reindeer: $totalReindeer")
          println(s"   Fit to fly: $totalCleared")
          println(s"   Not Flying: ${totalReindeer - totalCleared}\n")

          println(s"   Junior: ${juniorFlying.length}/${juniorTeam.length} fit to fly")
          println(s"   Experienced: ${experiencedFlying.length}/${experiencedTeam.length} fit to fly")
          println(s"   Elite: ${eliteFlying.length}/${eliteTeam.length} fit to fly\n")

          // Detailed breakdown
          println(" ***** JUNIOR REINDEER *****")
          juniorTeam.foreach { r =>
            val status = if (r.juniorReindeerFitToFly) "fit to fly" else "not flying"
            println(s"   ${r.name}: $status (Health: ${r.healthCheck}%, Fitness: ${r.juniorReindeerFitness})")
          }

          println("\n ***** EXPERIENCED REINDEER *****")
          experiencedTeam.foreach { r =>
            val status = if (r.experiencedReindeerFitToFly) "fit to fly" else "not flying"
            println(s"   ${r.name}: $status (Health: ${r.healthCheck}%, Fitness: ${r.experiencedReindeerFitness})")
          }

          println("\n ***** ELITE REINDEER *****")
          eliteTeam.foreach { r =>
            val status = if (r.eliteFitToFly) "fit to fly" else "not flying"
            println(s"   ${r.name}: $status (Health: ${r.healthCheck}%, Fitness: ${r.eliteReindeerFitness})")
          }
          println("\n ************************ End of Santa flight report ************************************ \n")
        }
      }
      }


  object ReindeerTrackerApp extends App { //App outside of everything else
    // Run code here

    // Create junior reindeer
    val rudolphJr = JuniorReindeer.createJuniorReindeer(
      "Rudolph Jr",
      4,
      CanFlyMagically(true),
      healthCheck = 85,
      FitnessLevel(9)
    )

    val blitzenJr = new JuniorReindeer(
      "Blitzen Jr.",
      2,
      CanFlyMagically(true),
      healthCheck = 65, // Below 68% :(
      FitnessLevel(6.9) // Below 7 :(
    )

    val cupidJr = JuniorReindeer.createJuniorReindeer(
      "Cupid Jr.",
      3,
      CanFlyMagically(true),
      healthCheck = 70,
      FitnessLevel(8.4)
    )

    val cometJr = JuniorReindeer.createJuniorReindeer(
      "Dasher Jr",
      6,
      CanFlyMagically(true),
      healthCheck = 83,
      FitnessLevel(8)
    )

    val dasherJr = JuniorReindeer.createJuniorReindeer(
      "Comet Jr",
      4,
      CanFlyMagically(true),
      healthCheck = 84,
      FitnessLevel(8)
    )

    // Create experienced reindeer
    val comet = ExperiencedReindeer.createExperiencedReindeer(
      "Comet",
      6,
      CanFlyMagically(true),
      healthCheck = 67,
      FitnessLevel(7.5),
      HoursFlown(450)
    )

    val vixen = new ExperiencedReindeer(
      "Vixen",
      7,
      CanFlyMagically(true),
      healthCheck = 70,
      FitnessLevel(8.0),
      HoursFlown(520)
    )

    val donner = ExperiencedReindeer.createExperiencedReindeer(
      "Donner",
      5,
      CanFlyMagically(true),
      healthCheck = 64, // Below 65%!
      FitnessLevel(6.5), // Below 7.0!
      HoursFlown(380)
    )

    // Create elite reindeer
    val rudolph = EliteReindeer.createEliteReindeer(
      "Rudolph",
      9,
      CanFlyMagically(true),
      healthCheck = 95,
      FitnessLevel(10.0),
      HoursFlown(1200),
      HasRedNose(true),
      "Glowing nose navigation"
    )

    val dasher = new EliteReindeer(
      "Dasher",
      10,
      CanFlyMagically(true),
      healthCheck = 62,
      FitnessLevel(6.5),
      HoursFlown(1500),
      HasRedNose(false),
      "Great speed when riding in front"
    )

    val prancer = EliteReindeer.createEliteReindeer(
      "Prancer",
      8,
      CanFlyMagically(true),
      healthCheck = 58, // Below 60%!
      FitnessLevel(5.5), // Below 6.0!
      HoursFlown(980),
      HasRedNose(false),
      "Can become invisible"
    )


    // All Reindeer list
    val juniorTeam: List[JuniorReindeer] = List(rudolphJr, blitzenJr, cupidJr, dasherJr, cometJr)
    val experiencedTeam: List[ExperiencedReindeer] = List(comet, vixen, donner)
    val eliteTeam: List[EliteReindeer] = List(rudolph, dasher, prancer)

    // Generate Santa's Report
    SantaReport.generateReport(juniorTeam, experiencedTeam, eliteTeam)
  }