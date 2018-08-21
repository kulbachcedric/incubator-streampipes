import { Component, OnInit, ViewChild } from '@angular/core';
import {AdapterDataSource} from '../all-adapters/adapter-data-source.service';
import {RestService} from '../rest.service';
import {AllAdaptersComponent} from '../all-adapters/all.component'
import { AdapterDescription } from '../model/connect/AdapterDescription';
@Component({
    selector: 'sp-main',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

    @ViewChild(AllAdaptersComponent) allAdapter: AllAdaptersComponent;
    private dataSource: AdapterDataSource;
    private restService: RestService;
    private selectedAdapter: AdapterDescription;

    public MainComponent(restService: RestService) {
        this.restService = restService;
    }

    ngOnInit() {
        this.dataSource = new AdapterDataSource(this.restService);
    }

    newAdapterCreated() {
        this.allAdapter.newAdapterStarted(); 
    }

    selectAdapter(adapter: AdapterDescription) {
        this.selectedAdapter = adapter;
    }
      

}
