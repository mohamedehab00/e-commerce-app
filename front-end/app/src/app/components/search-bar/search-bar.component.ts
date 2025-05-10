import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent implements OnInit {

  constructor(private route:Router) { }

  ngOnInit(): void {
  }

  search(keyword: string) {
    // Implement search logic here
    console.log('Search keyword:', keyword);
    
    this.route.navigate(['/products/search'], { queryParams: { keyword: keyword } });
  }
}
