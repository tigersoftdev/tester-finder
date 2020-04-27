import { Component, OnInit } from '@angular/core';
import { MatSelectChange } from '@angular/material/select';
import { Sort } from '@angular/material/sort';

import { ApiService } from './app.service';
import { Observable } from 'rxjs';
import { Device } from './interfaces/device';
import { Tester } from './interfaces/tester';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

	readonly allOption = 'ALL';
	readonly displayedColumns: string[] = ['firstName', 'lastName', 'bugs'];

	selectedCountries: Array<string> = [];
	selectedDevices: Array<number | string> = [];

	countries$: Observable<string[]>;
	devices$: Observable<Device[]>;

	testers: Tester[] = [];

	constructor(private readonly apiService: ApiService) {
	}

	ngOnInit(): void {
		this.countries$ = this.apiService.getCountries();
		this.devices$ = this.apiService.getDevices();
		this.find();
	}

	find(): void {
		const countries = this.getParam(this.selectedCountries);
		const devices = this.getParam(this.selectedDevices);
		this.apiService.getTesters(countries, devices)
			.subscribe(testers => this.testers = testers);
	}

	private getParam(selection) {
		return selection.includes(this.allOption) ? [] : selection;
	}

	countriesChanged($event: MatSelectChange) {
		if (this.selectedCountries.includes(this.allOption)) {
			this.selectedCountries = [this.allOption];
		}
	}

	devicesChanged($event: MatSelectChange) {
		if (this.selectedDevices.includes(this.allOption)) {
			this.selectedDevices = [this.allOption];
		}
	}

	onSort($event: Sort) {
		if ($event.direction) {
			this.testers = [...this.testers.sort((a, b) =>
				$event.direction === 'asc' ? a.bugs - b.bugs : b.bugs - a.bugs)];
		}
	}
}
