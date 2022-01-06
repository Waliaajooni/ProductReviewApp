import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { UserinfoComponent } from './userinfo.component';
import { ReviewpageComponent } from './reviewpage/reviewpage.component';

const routes: Routes = [
  {path: 'users', children: [
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegistrationComponent},
    {path: '', component: UserinfoComponent},
    {path: 'reviews', component: ReviewpageComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserinfoRoutingModule { }

//  {path: 'users', children: [
  //{path: 'login', component: LoginComponent},]}