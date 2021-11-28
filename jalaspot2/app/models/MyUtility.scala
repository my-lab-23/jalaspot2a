package my_utility

import sys.process._

object MyUtility {

   def ls(): String = {
      val data_path = sys.env("MY_DATA_PATH_SPOTIFY")
      val ls_out: String = s"ls $data_path".!!
      var ls_out_rep = ls_out.replace("\n", "<br>")
                             .replace("spotify.txt", "---")  
      ls_out_rep
   }
}
