import { Component, OnInit } from '@angular/core';
import { User } from '../User';
import { UserServiceService } from '../user-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user = new User();
  msg = ''

  constructor(private service: UserServiceService, private router: Router) {}

  ngOnInit(): void {
  }

  registerUser() {
    this.service.registerUserFromRemote(this.user).subscribe(
      data => {
        console.log("response received");
        this.router.navigate(['users/login']);
        this.msg = "User registered successfully";
        },
      error => {
        console.log("Exception occured");
        this.msg = "This user already exists";
      })
  }

}