import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatOption } from '@angular/material/core';
import { Sort } from '@angular/material/sort';

import { ApiService } from './app.service';
import { Subscription } from 'rxjs';
import { Device } from './interfaces/device';
import { Tester } from './interfaces/tester';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {

	readonly allOption = 'ALL';
	readonly displayedColumns: string[] = ['firstName', 'lastName', 'bugs'];

	selectedCountries: Array<string> = [];
	selectedDevices: Array<number | string> = [];

	countries: string[] = [];
	devices: Device[] = [];
	testers: Tester[] = [];

	countriesSubscription: Subscription;
	devicesSubscription: Subscription;

	@ViewChild('allCountries') private allCountriesOption: MatOption;
	@ViewChild('allDevices') private allDevicesOption: MatOption;

	constructor(private readonly apiService: ApiService) {
	}

	ngOnInit(): void {
		this.countriesSubscription = this.apiService.getCountries()
			.subscribe(countries => this.countries = countries);
		this.devicesSubscription = this.apiService.getDevices()
			.subscribe(devices => this.devices = devices);

		this.find();
	}

	ngOnDestroy(): void {
		this.countriesSubscription.unsubscribe();
		this.devicesSubscription.unsubscribe();
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

	togglePerOne(allOption: MatOption, selected: Array<string | number>, options: Array<string | Device>): void {
		if (allOption.selected) {
			allOption.deselect();
			return;
		}
		if (selected.length == options.length) {
			allOption.select();
		}
	}

	toggleAllCountries(): void {
		if (this.allCountriesOption.selected) {
			this.selectedCountries = [...this.countries, this.allOption];
		} else {
			this.selectedCountries = [];
		}
	}

	toggleAllDevices(): void {
		if (this.allDevicesOption.selected) {
			this.selectedDevices = [...this.devices.map(device => device.id), this.allOption];
		} else {
			this.selectedDevices = [];
		}
	}

	onSort($event: Sort) {
		if ($event.direction) {
			this.testers = [...this.testers.sort((a, b) =>
				$event.direction === 'asc' ? a.bugs - b.bugs : b.bugs - a.bugs)];
		}
	}
}
