package my_web

import my_utility.MyUtility

object MyWeb {
   object MyIndex {
      val title = "Jalaspot"
      val link1 =
         """<a href="/haydn">Haydn</a>
            <br>
            <a href="/mozart">Mozart</a>
            <br>
            <a href="/invernizzi">Roberta Invernizzi</a>
            <br>
            <a href="/faust">Isabelle Faust</a>
            <br>
            <a href="/haydn2032">Haydn 2032</a>
            <br>
            <a href="/otherForm">Other</a>"""
      val link2 =
         """<a href="/saveForm">Save</a>
            <br>
            <a href="/remove_web">Remove</a>
            <br>
            <a href="/dropbox">Dropbox</a>"""
   }

   object MyOther {
      val ls = s"<p>${MyUtility.ls()}</p>"
   }
}
