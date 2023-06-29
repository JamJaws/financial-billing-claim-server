import io.mateo.cxf.codegen.wsdl2java.Wsdl2Java
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    id("io.mateo.cxf-codegen") version "1.2.1"
}

group = "com.jamjaws.riv"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // cxf
    implementation("org.apache.cxf:cxf-spring-boot-starter-jaxws:4.0.2")
    implementation("org.apache.cxf:cxf-rt-features-logging:4.0.2")
    cxfCodegen("jakarta.xml.ws:jakarta.xml.ws-api:4.0.0")
    cxfCodegen("jakarta.annotation:jakarta.annotation-api:2.1.1")
    cxfCodegen("ch.qos.logback:logback-classic:1.2.10")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<Wsdl2Java>().configureEach {
    toolOptions {
        markGenerated.set(true)
    }
}

cxfCodegen {
    cxfVersion.set("4.0.0")
}

tasks.register("financialbillingclaim", Wsdl2Java::class) {
    toolOptions {
        wsdl.set(file("/Users/john/jamjaws/financial-billing-claim-server/src/main/resources/schemas/interactions/ProcessClaimSpecificationInteraction/ProcessClaimSpecificationInteraction_1.0_RIVTABP21.wsdl"))
        wsdlLocation.set("schemas/interactions/ProcessClaimSpecificationInteraction/ProcessClaimSpecificationInteraction_1.0_RIVTABP21.wsdl")
    }
}