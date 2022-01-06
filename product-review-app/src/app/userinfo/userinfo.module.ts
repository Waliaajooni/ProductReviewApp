import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { UserinfoRoutingModule } from './userinfo-routing.module';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { UserinfoComponent } from './userinfo.component';
import { ReviewpageComponent } from './reviewpage/reviewpage.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    LoginComponent,
    RegistrationComponent,
    UserinfoComponent,
    ReviewpageComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    NgbModule,
    UserinfoRoutingModule
  ]
})
export class UserinfoModule { }
