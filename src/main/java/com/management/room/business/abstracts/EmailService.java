package com.management.room.business.abstracts;

import com.management.room.business.requests.email.EmailDetails;

public interface EmailService {
	void send(EmailDetails details);
}