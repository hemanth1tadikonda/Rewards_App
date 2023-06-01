# rewardsapp

This is the rest end point to test this api

http://localhost:8181/RewardPoints/api/rewards


this is sample json response

[
    {
        "customerName": "Jeffery",
        "rewardsPerMonth": [
            {
                "month": "NOVEMBER",
                "rewards": 450
            }
        ],
        "totalRewardsEarned": 450
    },
    {
        "customerName": "Robert",
        "rewardsPerMonth": [
            {
                "month": "OCTOBER",
                "rewards": 158
            },
            {
                "month": "SEPTEMBER",
                "rewards": 90
            },
            {
                "month": "NOVEMBER",
                "rewards": 4
            }
        ],
        "totalRewardsEarned": 252
    },
    {
        "customerName": "Smith",
        "rewardsPerMonth": [
            {
                "month": "OCTOBER",
                "rewards": 250
            },
            {
                "month": "NOVEMBER",
                "rewards": 0
            }
        ],
        "totalRewardsEarned": 250
    }
]
# Rewards_App
# Rewards_App
# Rewards_App


Rewards_App where customer gets 2 points for each dollar  spends over $100 and 1 point for $50 in each transaction.
Calculating the rewards for each customer per month for 3 month period which includes all transactions.
