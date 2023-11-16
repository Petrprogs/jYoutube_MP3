<?php
// Set headers for file download
 header("Content-Type: application/json");
 exec("yt-dlp -x --embed-metadata --audio-format mp3 --audio-quality 320k --output '%(id)s.%(ext)s' https://www.youtube.com/watch?v=".$_GET["id"]);
 $files = glob($_GET["id"].'.mp3');
 echo '{"url":'.$files[0]."}";
 //exec("rm -R *.mp3")
?>