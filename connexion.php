<?php
  $connect = mysqli_connect("localhost","projet_groupe2","K!w!EstLaPlusBell3","projet_groupe20");
  $sql=mysqli_query($connect,"SELECT * FROM Clients");
  while($row=mysqli_fetch_assoc($sql))
  $output[]=$row;
  print(json_encode($output));
  mysqli_close($connect);
?>