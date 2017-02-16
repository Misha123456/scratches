package de.hybris.platform.accelerator.core

import de.hybris.platform.core.PK
import de.hybris.platform.core.model.user.CustomerModel
import de.hybris.platform.core.model.user.UserGroupModel
import de.hybris.platform.servicelayer.model.ModelService
import groovy.sql.Sql
import de.hybris.platform.commerceservices.enums.CustomerType

def start = System.currentTimeMillis()

def pathToSegmentsCsvFile = '/home/mikhail/Downloads/generate/userSegments'
def pathToSubscribersCsvFile = '/home/mikhail/Downloads/generate/insert_update_subscribers_'

def numberOfSubscribers = 200_000;

def buffer = 100000

def segments = ['segment1', 'segment2', 'segment3', 'segment4', 'segment5', 'segment6', 'segment7', 'segment8', 'segment9', 'segment10', 'segment11', 'segment12', 'segment13', 'segment14', 'segment15', 'segment16', 'segment17', 'segment18', 'segment19', 'segment20', 'segment21', 'segment22', 'segment23', 'segment24', 'segment25', 'segment26', 'segment27', 'segment28', 'segment29', 'segment30']

def firstNames = ['Ryan', 'Martin', 'Judy', 'Anne', 'Jerry', 'Fred', 'Bobby', 'Marilyn', 'Melissa', 'Donna', 'Ryan', 'Terry', 'Doris', 'Robin', 'Doris', 'Richard', 'Aaron', 'Jesse', 'Peter', 'Anne', 'Pamela', 'Ruby', 'Craig', 'Justin', 'Rose', 'Chris', 'Janice', 'David', 'Richard', 'Donna', 'Julia', 'Jane', 'Judith', 'Charles', 'Jeremy', 'Brenda', 'Evelyn', 'Denise', 'John', 'Julie', 'Harry', 'Helen', 'Teresa', 'Joshua', 'Helen', 'Katherine', 'Henry', 'Kenneth', 'Keith', 'Clarence', 'Maria', 'Juan', 'Keith', 'Scott', 'Victor', 'Kathy', 'Ruby', 'Carlos', 'Kathryn', 'Lois', 'Mary', 'Janice', 'Marie', 'Mark', 'Douglas', 'Todd', 'Harry', 'Katherine', 'John', 'Earl', 'Christina', 'Kimberly', 'Carolyn', 'Kathy', 'Sara', 'Victor', 'Pamela', 'Dennis', 'Marie', 'Douglas', 'Janet', 'Laura', 'Ryan', 'Fred', 'Joe', 'Jacqueline', 'Kathryn', 'Joseph', 'Nicholas', 'Brenda', 'John', 'Arthur', 'Jessica', 'Charles', 'Chris', 'Jerry', 'Eric', 'Jessica', 'Lori', 'William', 'Victor', 'Matthew', 'Jennifer', 'Johnny', 'Charles', 'Billy', 'Christopher', 'Amy', 'Shirley', 'Paul', 'Melissa', 'Paul', 'Alice', 'Ashley', 'Kathryn', 'Marie', 'Harold', 'Diane', 'Arthur', 'Brandon', 'Eugene', 'Mark', 'Kenneth', 'Lois', 'Kelly', 'Craig', 'Debra', 'Lawrence', 'Carol', 'Karen', 'Diana', 'Kelly', 'Donna', 'Kathleen', 'Judith', 'Nicholas', 'Jack', 'Joseph', 'Jerry', 'Todd', 'Louise', 'Alice', 'Scott', 'Anthony', 'Jacqueline', 'Larry', 'Virginia', 'Elizabeth', 'Aaron', 'Joshua', 'George', 'Laura', 'Paul', 'Susan', 'Cynthia', 'Phyllis', 'Helen', 'Ashley', 'Tammy', 'Frances', 'Kelly', 'Steve', 'Jason', 'Peter', 'Albert', 'Martha', 'Barbara', 'Debra', 'William', 'Charles', 'Martha', 'Shawn', 'Diane', 'Brenda', 'Helen', 'Stephen', 'Alan', 'Arthur', 'Doris', 'Lois', 'Donald', 'Joan', 'Eugene', 'Catherine', 'Randy', 'Arthur', 'Phillip', 'Donna', 'Amy', 'Frank', 'Virginia']
def lastNames = ['Hudson', 'Stone', 'Cunningham', 'Johnson', 'Scott', 'Hamilton', 'Porter', 'Brown', 'Morales', 'Edwards', 'Jacobs', 'Gibson', 'Roberts', 'Larson', 'Gilbert', 'Campbell', 'Adams', 'Burton', 'Fuller', 'Clark', 'Hughes', 'Duncan', 'Anderson', 'Lopez', 'Davis', 'Bell', 'Rodriguez', 'Robertson', 'Gutierrez', 'Gardner', 'Adams', 'Riley', 'Hicks', 'Hughes', 'Henry', 'Franklin', 'Williamson', 'Simmons', 'Morgan', 'Daniels', 'Collins', 'Matthews', 'Lopez', 'Elliott', 'Jenkins', 'Brown', 'Simmons', 'Frazier', 'Ellis', 'Shaw', 'Sanchez', 'Lee', 'Nguyen', 'Crawford', 'Henry', 'Riley', 'Gordon', 'Kelley', 'Torres', 'Myers', 'Harris', 'Gray', 'Black', 'Collins', 'Russell', 'Morris', 'Barnes', 'Foster', 'Lewis', 'Matthews', 'White', 'Larson', 'Brown', 'Bennett', 'Olson', 'Schmidt', 'Scott', 'Robinson', 'Hart', 'Harrison', 'Payne', 'Owens', 'Miller', 'Hunter', 'Reed', 'Larson', 'Stanley', 'Pierce', 'Anderson', 'West', 'Pierce', 'Welch', 'King', 'White', 'Morales', 'Reed', 'Morales', 'Welch', 'Miller', 'Powell', 'Henry', 'Black', 'Foster', 'Wood', 'Harrison', 'Phillips', 'Turner', 'Bryant', 'Martinez', 'Perez', 'Long', 'Lopez', 'Burns', 'Simmons', 'Brooks', 'Ferguson', 'Hughes', 'Williamson', 'Price', 'Crawford', 'Reid', 'Bell', 'Alvarez', 'Allen', 'Hernandez', 'Hanson', 'Griffin', 'Crawford', 'Hunt', 'Moore', 'Knight', 'Baker', 'Morgan', 'Reynolds', 'Jenkins', 'Mcdonald', 'Harvey', 'Hawkins', 'Bennett', 'Welch', 'Wood', 'Roberts', 'Alexander', 'Boyd', 'Perry', 'Fisher', 'Smith', 'Bennett', 'Alvarez', 'Powell', 'Brown', 'Smith', 'Cole', 'Jordan', 'Scott', 'Montgomery', 'Cruz', 'Greene', 'Chavez', 'Washington', 'Grant', 'Evans', 'Duncan', 'Warren', 'Fisher', 'Reid', 'Johnson', 'Moore', 'Owens', 'Jacobs', 'Sanchez', 'Reid', 'Davis', 'Gibson', 'Stephens', 'Palmer', 'Torres', 'Carroll', 'Lynch', 'Gordon', 'Rodriguez', 'Bradley', 'Stephens']
def fullNames = ['Peter Bennett', 'David Phillips', 'Joshua Mendoza', 'Brandon Baker', 'Walter Webb', 'George Hansen', 'Diana Davis', 'Carlos Peters', 'Charles Palmer', 'Ashley Kelley', 'Laura Bishop', 'Karen Wilson', 'Jeffrey Duncan', 'Jack Baker', 'Gloria Mendoza', 'Antonio Harvey', 'Arthur Dunn', 'Jeremy Reyes', 'Joyce Allen', 'Jane Lee', 'Donna Fields', 'Fred Patterson', 'Ann Williamson', 'Sharon Reed', 'Margaret Carr', 'Bobby Brown', 'Teresa Banks', 'Jonathan Griffin', 'Alan Castillo', 'Shirley Gonzales', 'William Butler', 'Sarah Holmes', 'Donald Morris', 'Linda Hill', 'Jeremy Bradley', 'Shawn Moore', 'Steve Wright', 'Brenda Harper', 'Carl Watson', 'Linda Evans', 'Timothy Stewart', 'Mark Medina', 'Eugene Howard', 'Arthur Ryan', 'Ernest Richards', 'Jack Gordon', 'Katherine Richards', 'Karen Perkins', 'Charles Carter', 'Harry Morris', 'Beverly Chapman', 'Pamela Sullivan', 'Kathryn Scott', 'Jeffrey Young', 'Pamela Cook', 'Karen Graham', 'Janice Hart', 'Jeffrey Carroll', 'Patricia Walker', 'Nicole Owens', 'Christine Oliver', 'Jane Mcdonald', 'Jesse Stephens', 'Maria Mitchell', 'Anna Griffin', 'Jack Chapman', 'Billy Woods', 'Roger Ryan', 'Shawn Hughes', 'Debra Bailey', 'Janice Fields', 'Frances Tucker', 'Eugene Duncan', 'Arthur Robertson', 'Christina Gutierrez', 'Martha Greene', 'Lisa Fisher', 'Anna Smith', 'Antonio Berry', 'Maria Andrews', 'Rachel Gomez', 'Carlos Gibson', 'Todd Thompson', 'Kathy Bell', 'Michelle Dean', 'Douglas Spencer', 'Douglas Black', 'Gerald Evans', 'Brandon Stevens', 'Jonathan Stanley', 'Walter Gardner', 'Rebecca Clark', 'Willie Grant', 'Martha Andrews', 'Robert Wallace', 'Ralph Cooper', 'Anne Montgomery', 'Steven Lopez', 'Kimberly Schmidt', 'Emily Brown', 'Roy Burns', 'Steven Morgan', 'Bruce Richardson', 'Victor Payne', 'Christine Fields', 'Jose Garrett', 'Martha Price', 'Joe Hernandez', 'Joshua Webb', 'Anne Taylor']
def companies = ['Purdy, Lind and Stoltenberg', 'Keebler LLC', 'Wunsch-Konopelski', 'Raynor, Brown and Mante', 'Fay Inc', 'Spencer LLC', 'Keeling and Sons', 'Monahan, Turner and Koelpin', 'Green, Vandervort and Trantow', 'Stark Group', 'Kuvalis, Bartell and Schroeder', 'Sauer, Schmeler and Lehner', 'Lang, Weber and Welch', 'Halvorson Group', 'Wilderman, Homenick and Langosh', 'Kautzer-Ankunding', 'Pollich-Hilpert', 'Murazik, Kuhlman and Pollich', 'Herzog Group', 'Orn-Schultz', 'Reinger LLC', 'Blick-Padberg', 'Torp, Williamson and Kerluke', 'Schoen-Grady', 'Conn LLC', 'Huels Group', 'Ward-Little', 'Stroman, Jenkins and West', 'Bode-Jast', 'Block-Mertz', 'Wisozk-Kuphal', 'Swift and Sons', 'Gulgowski LLC', 'Ortiz Inc', 'Blanda LLC', 'Kozey and Sons', 'Grady, Robel and Kilback', 'Moen, Schowalter and Pouros', 'Kohler-Tromp', 'Stehr Inc', 'Bailey-Kon', 'Hettinger-Reynolds', 'Cole-Dooley', 'Abbott-Schamberger', 'Schiller-Monahan', 'Abshire-Grady', 'Daniel-Schaefer', 'Ryan LLC', 'Corkery-Wehner', 'Bechtelar-Blanda', 'Champlin, Harvey and Bauch', 'Okuneva Inc', 'Stokes-Klein', 'Kutch Inc', 'Bashirian-Runolfsdottir', 'Nolan-Bosco', 'Wolf Inc', 'Kunde and Sons', 'Rodriguez-Keefe', 'Von LLC', 'Kuhic LLC', 'Conroy-Reichert', 'VonRueden-Mueller', 'Sporer-Sawayn', 'Rowe, Stokes and Cruickshank', 'Brown, Shields and Rohan', 'Farrell Group', 'Emmerich, Bradtke and Flatley', 'Zulauf LLC', 'Mayert, Kuvalis and Schimmel', 'Gutmann-Goyette', 'Satterfield-Connell', 'Little, Walter and Waters', 'Pouros, White and Treutel', 'Boehm, Kris and CoKeefe', 'Ziemann LLC', 'Collins-Shields', 'Tromp, Smitham and Greenholt', 'Zulauf-Skiles', 'Bogisich-Bashirian', 'Funk LLC', 'Olson LLC', 'Jacobs, Sporer and Gleichner', 'Ledner and Sons', 'Boyer, Lubowitz and Emmerich', 'Beatty-Schaefer', 'Macejkovic, Dach and Bernhard', 'Rath-Crona', 'Barrows, Balistreri and Windler', 'Luettgen-Stark']

def msisdn
def email
def firstName
def lastName
def middleName
def unitUID
def unitName
def isResident
def type

def pk

def subSegment
def uidPrefix = 'sb_'
def nameprefix = 'subscriber_'
def uid
def name
List<String> buf = new ArrayList<>()

Random random = new Random();
List<String> listUid = new ArrayList<String>();

def now = new Date()
def timestamp = now.toTimestamp().time
File segmentsFile = new File(pathToSegmentsCsvFile + '-' + timestamp + '.csv')
File subscribersFile = new File(pathToSubscribersCsvFile + timestamp + '.csv')


1.upto(numberOfSubscribers){
    pk = PK.createCounterPK((int) 1).getLongValue();
    uid = uidPrefix + pk
    name = nameprefix + it

    listUid.add uid

    if (it < buffer || it % buffer == 0 || (numberOfSubscribers - it)< buffer) {
        listUid.each {
            Collections.shuffle(segments, random);
            subSegment = segments.subList(0, random.nextInt(segments.size()) + 1);

            msisdn = it
            buf.add(msisdn)

            email = "jhenry" + msisdn + "@marriott.com"
            buf.add(email)

            firstName = firstNames.get(random.nextInt(firstNames.size())) + '_' + it
            buf.add(firstName)

            lastName = lastNames.get(random.nextInt(lastNames.size())) + '_' + it
            buf.add(lastName)

            middleName = lastNames.get(random.nextInt(lastNames.size())) + '_' + it
            buf.add(middleName)

            unitUID = UUID.randomUUID().toString()
            buf.add(unitUID)


            if (random.nextBoolean()) {
                unitName = companies.get(random.nextInt(companies.size())) + '_' + it
                buf.add(unitName)

                buf.add(" ")

                type = 'LEGAL'
                buf.add(type)
            } else {
                unitName = fullNames.get(random.nextInt(fullNames.size())) + '_' + it
                buf.add(unitName)

                isResident = random.nextBoolean();
                buf.add(isResident)

                type = 'INDIVIDUAL'
                buf.add(type)
            }

            subscribersFile << '"' + buf.join('","') + '"\n'
            buf.clear()

            segmentsFile << '"' + it + '", "' + subSegment.join('|') + '"\n'
        }
        listUid.clear();
    }
}


now = System.currentTimeMillis()
println "Execution took ${now - start} ms"

println 'generated file with user segments is here: ' + segmentsFile.absolutePath
println 'generated file with subscribers is here: ' + subscribersFile.absolutePath
