import { Route } from '@angular/compiler/src/core';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user_service';

@Component({
  selector: 'app-verify',
  templateUrl: './verify.component.html',
  styleUrls: ['./verify.component.css']
})
export class VerifyComponent implements OnInit {

  username:string = "";

  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
      this.userService.verify(this.route.snapshot.paramMap.get('code')!).subscribe(
        (user: User)=>{
              this.username = user.username;
              this.router.navigate([`/verifySuccessful`]);
        },
        (error) =>{
          this.router.navigate([`/verifyNotSuccessful`]);
        }
      )
  }

}
