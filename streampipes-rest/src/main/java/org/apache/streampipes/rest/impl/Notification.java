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

package org.apache.streampipes.rest.impl;

import org.apache.streampipes.model.message.Notifications;
import org.apache.streampipes.rest.core.base.impl.AbstractRestResource;
import org.apache.streampipes.rest.shared.annotation.GsonWithIds;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v2/users/{username}/notifications")
public class Notification extends AbstractRestResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @GsonWithIds
    public Response getNotifications(@QueryParam("notificationType") String notificationTypeId,
                                     @QueryParam("offset") Integer offset,
                                     @QueryParam("count") Integer count) {
        return ok(getNotificationStorage()
                .getAllNotifications(notificationTypeId, offset, count));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/count")
    public Response getUnreadNotificationsCount(@PathParam("username") String username) {
        return ok(getNotificationStorage()
                .getUnreadNotificationsCount(username));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/unread")
    public Response getUnreadNotifications() {
        return ok(getNotificationStorage()
                .getUnreadNotifications());
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{notificationId}")
    public Response deleteNotification(@PathParam("notificationId") String notificationId) {
        boolean success = getNotificationStorage()
                .deleteNotification(notificationId);
        if (success) {
            return ok(Notifications.success("Notification deleted"));
        } else {
            return ok(Notifications.error("Could not delete notification"));
        }

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{notificationId}")
    public Response modifyNotificationStatus(@PathParam("notificationId") String notificationId) {
        boolean success = getNotificationStorage()
                .changeNotificationStatus(notificationId);
        if (success) {
            return ok(Notifications.success("Ok"));
        } else {
            return ok(Notifications.error("Error"));
        }
    }
}
