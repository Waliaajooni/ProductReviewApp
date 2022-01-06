import { Component, OnInit } from '@angular/core';
import { User } from '../User';
import { UserServiceService } from '../user-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user = new User();
  msg = '';
  regusers : User[];

  constructor(private service: UserServiceService, private router: Router) { }

  ngOnInit(): void {
  }

  loginUser() {
    console.log(this.user);
    this.service.loginUserFromRemote(this.user).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['users']);
        sessionStorage.setItem("Authorization", "Bearer "+data.jwt);
        },
      error => {
        console.log("Exception occured");
        this.msg = "Bad Credentials, Either emailId or Password is wrong";
      })
  }

}
