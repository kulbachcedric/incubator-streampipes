/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import { Component, OnInit } from '@angular/core';
import { SpAbstractAdapterDetailsDirective } from '../abstract-adapter-details.directive';
import { AuthService } from '../../../../services/auth.service';
import { ActivatedRoute } from '@angular/router';
import { AdapterService, AdapterMonitoringService, SpLogEntry } from '@streampipes/platform-services';

@Component({
  selector: 'sp-adapter-details-logs',
  templateUrl: './adapter-details-logs.component.html',
  styleUrls: ['./adapter-details-logs.component.scss']
})
export class SpAdapterDetailsLogsComponent extends SpAbstractAdapterDetailsDirective implements OnInit {

  adapterLogs: SpLogEntry[];

  constructor(authService: AuthService,
              activatedRoute: ActivatedRoute,
              adapterService: AdapterService,
              adapterMonitoringService: AdapterMonitoringService) {
    super(authService, activatedRoute, adapterService, adapterMonitoringService);
  }

  ngOnInit(): void {
    super.onInit();
  }

  loadLogs(): void {
    this.adapterMonitoringService.getLogInfoForAdapter(this.currentAdapterId).subscribe(res => {
      this.adapterLogs = res;
    });
  }

  onAdapterLoaded(): void {
  }

}
