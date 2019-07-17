/*
 * Copyright 2019 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.netflix.spinnaker.clouddriver.saga.persistence

import com.netflix.spinnaker.clouddriver.saga.SagaEvent
import com.netflix.spinnaker.clouddriver.saga.models.Saga

/**
 * Provides a thin DSL above [EventRepository] for persisting and retrieving Sagas.
 */
interface SagaRepository {

  fun list(criteria: ListCriteria): List<Saga>

  /**
   * Get a [Saga] by its [type] and [id].
   *
   * @param type The type of Saga (e.g. awsDeploy, awsCreateLoadBalancer, etc)
   * @param id The specific ID of the Saga
   */
  fun get(type: String, id: String): Saga?

  /**
   * Save a [Saga] as a [SagaSaved] event.
   */
  fun save(saga: Saga, additionalEvents: List<SagaEvent>? = null)

  data class ListCriteria(
    val running: Boolean?
  )
}
