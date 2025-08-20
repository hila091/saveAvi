package hanged_man_game;

import java.util.Scanner;

public class mainScript {

	public static void main(String[] args) {			
			Scanner input = new Scanner(System.in);
			System.out.println("ברוכים הבאים למשחק החלקים של אבי");
			System.out.println("בחר רמת קושי:");
			System.out.println("2=קלה =1 קשה =3 בינונית");
			int level= input.nextInt();// מספר ניחושים
			System.out.println("הכירו את אבי, גבר ציוני משכמו ומעלה שמוכן לתת ממיטבו לאומה");
			System.out.println("בשביל להציל את חייו היקרים של אבי ברשותך 10 ניסיונות כושלים לניחוש אותיות");
			System.out.println("ברגע שתיכשלו ירד ניסיון אחד, וכאשר תצליחו לנחש אות נכונה תופיע המחשה של המילה");
			String hiddenword =hebrewRndom (level);
			//System.out.println(hiddenword);// לא לשכוח למחוק
			int charCount = hiddenword.length();//אורך המילה
			char[] word = new char[charCount];
			char[] tempResult = new char[charCount];// מערך בגודל המילה
	 
			for (int i = 0; i< tempResult.length;i++) //מערך ההמחשה
				tempResult[i] = '_';	
			
			for (int i = 0; i< word.length;i++)// מערך המחשה של המילה מפורקת בשביל כפילויות
				word[i]= hiddenword.charAt(i);
			
			
			for (int j = tempResult.length - 1 ; j>=0; j--) 
				System.out.print(tempResult[j] + " ");
			
			
			int life=10;// מספר ניחושים
			
			while(life>0) {//הלולאה הכללית של מספר הניחושים שיש 
				char nextGuess = input.next().charAt ( 0 );//קליטת ניחוש
				
				int placeGuesss = hiddenword.indexOf(nextGuess);//מיקום האות במילה 
				
				if (placeGuesss== -1) { // אם האות לא נמצאת במילה
					life--;
	                if(life==0) {
	                    System.out.println("צר לנו להודיע, כי לא צלחת במשימה וניסיונותיך להציל את החלקים של אבי הגיעו לקיצם");
	                    System.out.println(" :המילה הנסתרת שלנו היתה  "+hiddenword);
	                    man(life);	
	                }
	                else {	
					System.out.println ("האות שניחשת לא נמצאת במילה הנסתרת, הי לכך ובהתאם לזאת מצבו של אבי הידרדר");
					man(life);
					System.out.println( "נותרו לך  "+ life +" ניחושים כושלים");
				
	                }
				}
				else {
					if(emptyOrNot(tempResult)==1){// אם זה הניחוש הראשון הנכון ולפני כן לא נכנסה אות למערך
						System.out.println("ברכותינו האות שניחשת כן נמצאת במילה");
						putIntoArray(tempResult,nextGuess,word);//	מכניס למערך הרווחים את האות במקום הנכון שלה			
					for (int j = tempResult.length - 1 ; j>=0; j--) 
							System.out.print(tempResult[j] + " ");
					}
					else {//זה לא הניחוש הראשון ויש כבר אות בתוך המערך 
						putIntoArray(tempResult,nextGuess,word);//	מכניס למערך הרווחים את האות במקום הנכון שלה
						
						if (emptyOrNot(tempResult)==3) {// אם יש רק אותיות בהמחשה והמשתמש הצליח לנחש את כל המילה
						System.out.println(" ברכותינו הצלחת לנחש את המילה "+hiddenword+" ובכך להציל את אבי ממוות בטוח ");
						System.out.println("בשארית ימיו אבי תרם רבות לחקר הריבוזום והאטום הגרעיני וכך שוב הצדק נעשה");
						man(100);
						life=0;
						}
						else {
						System.out.println();
						System.out.println("ברכותינו האות שניחשת כן נמצאת במילה");
						printreverse (tempResult );//  מדפיס את ההמחשה בצורה הנכונה בהופכיות ביחס לאות הראשונה והאחרונה בעברית
						
						}
					}
					System.out.println();
								
				}
			}
			
			
			System.out.println("וכך הגיע לסיומו עוד משחק פרוע ומותח של אבי חלקים!! תודה שהשתתפתם");
		}
			
			public static int emptyOrNot(char[]tempResult) {//פעולה שמחזירה1 אם המערך ריק מאותיות 2אם הוא מעורב ו3 אם יש בו רק אותיות 
				int count=0;
				for (int i=0;i<tempResult.length;i++)
					if (tempResult[i]!='_')
						count++;//סופר את כמות האותיות
				if(count==0)
				return 1;
				if(count==tempResult.length)
					return 3;
				return 2;
			}
			 
			public static void printreverse ( char tempResult [] ) { // הפעולה מדפיסה מהאות האחרונה עד לאות הראשונה את המערך הפוך
				for(int j=tempResult.length-1;j>lasttWord(tempResult);j--)// חלק אחד להדפיס את כל הרווחים לפני האות האחרונה בעברית
					System.out.print(tempResult[j] + " ");
				
			int first=firstWord(tempResult);
			int last=lasttWord( tempResult );
				for(int i= first; i<=last;i++)
		System.out.print(tempResult [i]+" ");
				
				for( int j=firstWord(tempResult)-1;j>=0;j--)// חלק שלוש להדפיס את הרווחים שנשארו מהאות הראשונה  בעברית עד תחילת המילה 
					System.out.print(tempResult[j] + " ");

		}
		
			public static int firstWord(char tempResult []) {
				int i=0;
					while (tempResult[i]=='_')
						i++;
					return i;
					
			}
		 

			public static int lasttWord(char tempResult []) {
				int i=tempResult.length-1;
					while (tempResult[i]=='_')
					i--;
					return i;
					
			}
		 
		
			
			
			
			
		public static void putIntoArray(char tempResult [],char nextGuess, char word[] ) {// פעולה שמכניבה את האות שניחש למערך ההמחשה- בגלל הכפילויות
		for(int i=0;i<tempResult.length;i++)
		if(word[i]==nextGuess)
			tempResult[i]=nextGuess;
		}
		
		
		public static String hebrewRndom (int num) {
			String[] easy = new String[50];
			String medium []=new String[50];
			String hard []=new String[50];
				 easy = new String[]{"כלב","חתול","שמש","חול","בוץ","קיץ","ספר","שדה","צבע","גוזל","גג","קרקס","תנור","רצון","שולחן","מפה","גשם","רעש","מדינה","צעיף","חרוז","כדור","צפרדע","זקנה","עומר","צנון","מרק","ארץ","אהבה","מלחמה","שלום","מחשב","נמלה","ראש","תינוק","דלת","תהום","שקט","זמר","איש","דודה","רב","מלפפון","צפרדע","גלגל","כפכף","תוף","געגוע","ענב"};
				 medium = new String[]{"מנורה","קלסר","שירה","מחול","משאית","אתמול","תקרה","מכונה","חילזון","מחוש","עונש","כוכב","הדפסה","מחברת","טלאי","התגלגלות","להתראות","חוויה","קריאה","בקבוק","שיחה","מפית","אננס","מלצרית","קרוב","מכונה","קלטת","שינאה","שקם","דורבן","שמיכה","בריכה","שיחה","צעצוע","אפרוח","משחק","כיסא","תרנגול","סביבון","מכשפה","התגלגלות","זללנות","שטרודל","קאפקייק","צופר","אבזם","כינור","כיריים","מלאך","כפפות"};
				 hard= new String[] {"אולימפיאדה","טרקטורון","עצמאות","מינוף","קריירה","לונדון","הרמוניה","מדרשה","לוליינית","הדרן","אנרכייה","התרגשות","גיאוגרפיה","אטמוספרה","פנטומימה","טמפרטורה","השקעה","קלרינט","דיקטטור","אמבולנס","אוניברסיטה","דיסק","תקליט","פטיפון","להשתכנע","ניצרה","ליבה","מספרים","אחשוורוש","בדרן","משמעות","ברברנות","קליטה","מגונדר","חקלאי","התמרמרות","קארדיגן","קונספירציה","חמרמורת","אינפורמציה","אמנציפציה","פסיכומטרי","קטורת","מרשתת","קליידוסקופ","הורוסקופ","מדיטציה","אינטרפטציה","פרשנות","טלפון"};
				int randomNum = (int)(Math.random() * 51);
				if(num==1) 
			    	return 	easy[randomNum];
				if (num==2)
					return medium[randomNum];
				return hard [randomNum];
				}

		public static void man(int life) {
			if(life==9) {
	    	System.out.println(  "          ");
			System.out.println(  "          ");
			System.out.println(  "          ");
			System.out.println(  "   ▁▁▁   ");
			}
			if(life==8) {
			System.out.println(  "     |    ");
			System.out.println(  "     |    ");
			System.out.println(  "     |    ");
			System.out.println(  "   ▁▁▏▁   ");
			}
			
			if(life==7) {
				System.out.println(  "  ▁▁    ");
				System.out.println(  " ╵  |    ");
				System.out.println(  "    |    ");
				System.out.println(  "    |    ");
				System.out.println(  "    |    ");
				System.out.println(  "  ▁▁▏▁   ");
			}
			if(life==6) {
				System.out.println(  "  ▁▁    ");
				System.out.println(  " ╵  |    ");
				System.out.println(  " Ｏ  |    ");
				System.out.println(  "    |    ");
				System.out.println(  "    |    ");
				System.out.println(  "  ▁▁▏▁   ");
				}
			if(life==5) {
				System.out.println(  "  ▁▁    ");
				System.out.println(  " ╵  |    ");
				System.out.println(  " Ｏ  |    ");
				System.out.println(  "  ◝ |    ");
				System.out.println(  "    |    ");
				System.out.println(  "  ▁▁▏▁   ");
			}
			if(life==4) {
				System.out.println(  "  ▁▁    ");
				System.out.println(  " ╵  |    ");
				System.out.println(  " Ｏ  |    ");
				System.out.println(  "◜ ◝ |    ");
				System.out.println(  "    |    ");
				System.out.println(  "  ▁▁▏▁   ");
				}
			if(life==3) {
				System.out.println(  "  ▁▁    ");
				System.out.println(  " ╵  |    ");
				System.out.println(  " Ｏ  |    ");
				System.out.println(  "◜┃◝ |    ");
				System.out.println(  "    |    ");
				System.out.println(  "  ▁▁▏▁   ");
				}
			if(life==2) {
				System.out.println(  "  ▁▁    ");
				System.out.println(  " ╵  |    ");
				System.out.println(  " Ｏ  |    ");
				System.out.println(  "◜┃◝ |    ");
				System.out.println(  "  ⁾ |    ");
				System.out.println(  "  ▁▁▏▁   ");			
				}
			if(life==1) {
				System.out.println(  "  ▁▁    ");
				System.out.println(  " ╵  |    ");
				System.out.println(  " Ｏ  |    ");
				System.out.println(  "◜┃◝ |    ");
				System.out.println(  "⁽ ⁾ |     ");
				System.out.println(  "  ▁▁▏▁   ");
				}
			if(life==0) {
				System.out.println(  "   ▁▁    ");
				System.out.println(  " ✃╵  |    ");
				System.out.println(  " ☠️  |    ");
				System.out.println(  " ◜┃◝ |    ");
				System.out.println(  " ⁽ ⁾ |    ");
				System.out.println(  "   ▁▁▏▁   ");
				}
			if (life==100) {
				System.out.println(  "  *ריקוד ניצחון*  ");
				System.out.println(  "     🎓       ");
				System.out.println(  "  ☝ Ｏ  👉   ");
				System.out.println(  "   ╰ ┃ ⏝       ");
				System.out.println(  "    ╭  ╮      ");
				System.out.println(  "▁▁▁▁▁▁▁▁▁");
		}

	}

}
